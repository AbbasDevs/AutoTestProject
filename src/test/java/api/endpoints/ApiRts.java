package api.endpoints;

public class ApiRts {
    
    public static String base_url = "https://automationexercise.com/api";
    
    // Products & Brands
    public static String prdsList_url = base_url + "/productsList";
    public static String brndsList_url = base_url + "/brandsList";
    public static String srchPrd_url = base_url + "/searchProduct";
    
    // User Account
    public static String vrfyLgn_url = base_url + "/verifyLogin";
    public static String crtAcc_url = base_url + "/createAccount";
    public static String updAcc_url = base_url + "/updateAccount";
    public static String delAcc_url = base_url + "/deleteAccount";
    public static String getUsr_url = base_url + "/getUserDetailByEmail";
}
