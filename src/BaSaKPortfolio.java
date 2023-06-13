public class BaSaKPortfolio {
    static UserDAO DAO = new UserDAO();
    LogInFrame lf = null;
    public static void main(String[] args) {
        BaSaKPortfolio BaSak = new BaSaKPortfolio();
        BaSak.lf = new LogInFrame(DAO);
        BaSak.lf .setVisible(true);
    }
}
