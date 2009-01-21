package toastwars.server;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;

import toastwars.client.ToastWarsService;
import toastwars.server.dao.DAOGame;
import toastwars.server.dao.DAOToaster;
import toastwars.server.dao.DAOUser;
import toastwars.server.dao.DBConnection;
import toastwars.server.datamodel.core.Company;
import toastwars.server.datamodel.core.Game;
import toastwars.server.datamodel.core.MarketResearchReport;
import toastwars.server.datamodel.core.Toaster;
import toastwars.server.datamodel.core.Type;
import toastwars.server.datamodel.user.Group;
import toastwars.server.datamodel.user.IUser;
import toastwars.server.datamodel.user.Master;
import toastwars.server.datamodel.user.Status;
import toastwars.server.datamodel.user.UserFactory;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class ToastWarsServiceImpl extends RemoteServiceServlet implements
		ToastWarsService {

	private static final long	serialVersionUID			= 1L;
	private Master				master;
	// @gwt.typeArgs <Number>
	private ArrayList<Number>	profitRankingList			= null;
	// @gwt.typeArgs <Number>
	private ArrayList<Number>	capitalRankingInternList	= null;

	@Override
	public void init() throws ServletException {
		super.init();
		master = (Master) UserFactory.createUser("Master", "Master", "master");
		// connectToDB
		Connection con = DBConnection.getInstance().connectToDB();
		if (DAOGame.isGameStarted(con)) {
			try {
				Game game = Game.getInstance(DAOGame.getUserAmount(con));
				game.setCurrentRound(DAOGame.getCurrentRound(con));
				game.setGroupList(DAOGame.getAllUsers(con));

				int round = Game.getInstance().getCurrentRound();
				if (round > 1)
					round--;
				ArrayList<Group> groupList4Report = DAOGame.getAllUsersByRound(
						con, round);

				// close DB connection
				DBConnection.getInstance().closeConnectionToDB(con);

				master.setGame(Game.getInstance());

				MarketResearchReport report = MarketResearchReport
						.getInstance();
				ArrayList<Group> grouplist = Game.getInstance().getGroupList();

				report.generateMarketResearchReport(groupList4Report);
				this.capitalRankingInternList = report
						.getCapitalRankingInternList();
				this.profitRankingList = report.getProfitRankingInternList();

				for (int i = 0; i < grouplist.size(); i++) {
					grouplist.get(i).getCompany().setProfitRankingList(
							this.profitRankingList);
					grouplist.get(i).getCompany().setCapitalRankingInternList(
							this.capitalRankingInternList);

					Company company = grouplist.get(i).getCompany();
					company.setReportListe(null);

					if (company.isMarketResearchReportON())
						company.setReportListe(report.getReports());

					company.setMarketResearchReportON(false);
				}
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
				// connectToDB
				Connection con = DBConnection.getInstance().connectToDB();
				user = DAOUser.findUser(name, pwd, con);
				// close DB connection
				DBConnection.getInstance().closeConnectionToDB(con);
				if (((Group) user).isOnline())
					return null;
				((Group) user).setOnline(true);
			} catch (Exception e) {
				return null;
			}
		return user;
	}

	public Game getCurrentGame() {
		// connectToDB
		Connection con = DBConnection.getInstance().connectToDB();
		if (DAOGame.isGameStarted(con)) {
			// close DB connection
			DBConnection.getInstance().closeConnectionToDB(con);
			return Game.getInstance();
		}

		return null;
	}

	public Boolean logout(String name, String pwd) {
		try {
			// connectToDB
			Connection con = DBConnection.getInstance().connectToDB();
			((Group) DAOUser.findUser(name, pwd, con)).setOnline(false);
			// close DB connection
			DBConnection.getInstance().closeConnectionToDB(con);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public Game startGame(int userAmount) {
		// connectToDB
		Connection con = DBConnection.getInstance().connectToDB();
		if (!DAOGame.isGameStarted(con)) {
			try {
				Game.getInstance(userAmount);
				Type.TYPE1.setMarketVolumeTT1(userAmount);
				Type.TYPE2.setMarketVolumeTT2(userAmount);
				Type.TYPE3.setMarketVolumeTT3(userAmount);
				DAOGame.createInitialData(userAmount, con);
				Game.getInstance().setUserAmount(userAmount);
				Game.getInstance().setCurrentRound(1);
				Game.getInstance().setGroupList(DAOGame.getAllUsers(con));
				// TODO: gibt eine Liste mit Companys = null zurück!!!
				ArrayList<Group> groupList4Report = DAOGame.getAllUsersByRound(
						con, Game.getInstance().getCurrentRound());

				// close DB connection
				DBConnection.getInstance().closeConnectionToDB(con);
				master.setGame(Game.getInstance());

				MarketResearchReport report = MarketResearchReport
						.getInstance();
				ArrayList<Group> grouplist = Game.getInstance().getGroupList();

				report.generateMarketResearchReport(groupList4Report);
				this.capitalRankingInternList = report
						.getCapitalRankingInternList();
				this.profitRankingList = report.getProfitRankingInternList();
				for (int i = 0; i < grouplist.size(); i++) {
					grouplist.get(i).getCompany().setProfitRankingList(
							this.profitRankingList);
					grouplist.get(i).getCompany().setCapitalRankingInternList(
							this.capitalRankingInternList);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return Game.getInstance();
		}
		return null;
	}

	public Boolean endGame() {
		// connectToDB
		Connection con = DBConnection.getInstance().connectToDB();
		boolean b = DAOGame.resetGame(con);
		// close DB connection
		DBConnection.getInstance().closeConnectionToDB(con);
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
		// connectToDB
		Connection con = DBConnection.getInstance().connectToDB();

		if (DAOGame.isGameStarted(con)) {
			try {

				ArrayList<Group> grouplist = Game.getInstance().getGroupList();

				for (int a = 0; a < grouplist.size(); a++) {
					if (grouplist.get(a).getStatus() != Status.INACTIVE)
						grouplist.get(a).getCompany().calculateIndex();
				}

				// nötig für die Berechnung mit Lagerverwaltung
				MarketResearchReport report = MarketResearchReport
						.getInstance();
				Game.getInstance().setSortedIndexListTyp1(
						report.getSortedIndexListTyp1());
				Game.getInstance().setSortedIndexListTyp2(
						report.getSortedIndexListTyp2());
				Game.getInstance().setSortedIndexListTyp3(
						report.getSortedIndexListTyp3());

				Master.getInstance().simulate();
				DAOGame.saveAllUsers(grouplist, con);
				// TODO: gibt eine Liste mit Companys = null zurück!!!
				ArrayList<Group> groupList4Report = DAOGame.getAllUsersByRound(
						con, Game.getInstance().getCurrentRound());
				// close DB connection
				DBConnection.getInstance().closeConnectionToDB(con);

				// extra report
				report.generateMarketResearchReport(groupList4Report);
				this.capitalRankingInternList = report
						.getCapitalRankingInternList();
				this.profitRankingList = report.getProfitRankingInternList();
				for (int i = 0; i < grouplist.size(); i++) {
					grouplist.get(i).getCompany().setProfitRankingList(
							this.profitRankingList);
					grouplist.get(i).getCompany().setCapitalRankingInternList(
							this.capitalRankingInternList);
					Company company = grouplist.get(i).getCompany();
					company.setReportListe(null);

					if (company.isMarketResearchReportON())
						company.setReportListe(report.getReports());
					company.setMarketResearchReportON(false);
				}

				// reset user input parameters
				for (Group group : grouplist) {
					ArrayList<Toaster> toasterList = group.getCompany()
							.getToasterList();
					for (Toaster toaster : toasterList) {
						toaster.resetUserInput();
					}
				}

				return Game.getInstance();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	public Boolean save(Group group) {
		// connectToDB
		Connection con = DBConnection.getInstance().connectToDB();
		boolean success = DAOUser.updateUser(group, con);
		Game.getInstance().setGroupList(DAOGame.getAllUsers(con));

		if (group.getStatus() == Status.COMPLETED) {
			Game.getInstance().completeRound(group);
			DAOUser.updateUser(group, con);
		}
		// close DB connection
		DBConnection.getInstance().closeConnectionToDB(con);
		ArrayList<Group> grouplist = Game.getInstance().getGroupList();
		for (int i = 0; i < grouplist.size(); i++) {
			grouplist.get(i).getCompany().setProfitRankingList(
					this.profitRankingList);
			grouplist.get(i).getCompany().setCapitalRankingInternList(
					this.capitalRankingInternList);
		}
		return success;
	}

	public Boolean createNewToaster(ArrayList<Toaster> toasterList,
			int companyID) {

		ArrayList<Toaster> outList = toasterList;
		Connection con = DBConnection.getInstance().connectToDB();
		DAOToaster dao = new DAOToaster();

		try {
			for (Toaster toaster : outList) {
				dao.saveToaster(toaster, companyID, con);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		// close DB connection
		DBConnection.getInstance().closeConnectionToDB(con);

		return true;
	}
}
