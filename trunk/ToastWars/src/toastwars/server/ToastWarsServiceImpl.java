package toastwars.server;

import java.util.ArrayList;

import javax.servlet.ServletException;

import toastwars.client.ToastWarsService;
import toastwars.server.dao.DAOGame;
import toastwars.server.dao.DAOUser;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.MarketResearchReport;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.IUser;
import toastwars.server.datamodel.user.Master;
import toastwars.server.datamodel.user.Status;
import toastwars.server.datamodel.user.UserFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ToastWarsServiceImpl extends RemoteServiceServlet implements
		ToastWarsService {

	private static final long	serialVersionUID	= 1L;
	private Master				master;

	@Override
	public void init() throws ServletException {
		super.init();
		master = (Master) UserFactory.createUser("Master", "Master", "master");
		if (DAOGame.isGameStarted()) {
			try {
				Game game = Game.getInstance(DAOGame.getUserAmount());
				game.setCurrentRound(DAOGame.getCurrentRound());
				game.setGroupList(DAOGame.getAllUsers());
				master.setGame(Game.getInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public IUser login(String name, String pwd) {

		IUser user = null;

		if (name.equals(master.getUsername())
				&& pwd.equals(master.getPassword()))
			user = master;
		else
			try {
				user = DAOUser.findUser(name, pwd);
				if (((Group) user).isOnline())
					return null;
				((Group) user).setOnline(true);
			} catch (Exception e) {
				return null;
			}
		return user;
	}

	public Game getCurrentGame() {
		if (DAOGame.isGameStarted())
			return Game.getInstance();

		return null;
	}

	public Boolean logout(String name, String pwd) {
		try {
			((Group) DAOUser.findUser(name, pwd)).setOnline(false);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Game startGame(int userAmount) {
		if (!DAOGame.isGameStarted()) {
			try {
				Game.getInstance(userAmount);
				DAOGame.createInitialData(userAmount);
				Game.getInstance().setUserAmount(userAmount);
				Game.getInstance().setCurrentRound(1);
				Game.getInstance().setGroupList(DAOGame.getAllUsers());
				master.setGame(Game.getInstance());
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Game.getInstance();
		}
		return null;
	}

	public Boolean endGame() {
		boolean b = DAOGame.resetGame();

		if (b) {
			try {
				Game.getInstance().getGroupList().clear();
				master.setGame(null);
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}

	public Game simulate() {
		if (DAOGame.isGameStarted()) {
			try {
				Master.getInstance().simulate();

				ArrayList<Group> groupList = Game.getInstance().getGroupList();
				DAOGame.saveAllUsers(groupList);

				MarketResearchReport report = MarketResearchReport
						.getInstance();
				report.generateMarketResearchReport(groupList);

				for (int i = 0; i < groupList.size(); i++) {
					Company company = groupList.get(i).getCompany();
					company.setReportListe(null);

					if (company.isMarketResearchReportON())
						company.setReportListe(report.getReports());

					company.setMarketResearchReportON(false);
				}

				return Game.getInstance();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Boolean save(Group group) {
		boolean success = DAOUser.updateUser(group);
		Game.getInstance().setGroupList(DAOGame.getAllUsers());

		if (group.getStatus() == Status.COMPLETED)
			Game.getInstance().completeRound(group);

		return success;
	}
}
