package businesslogic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class ReportService {

    public static void main(String args[]) throws IOException {
        connection();
    }

    public static String connection() throws MalformedURLException, IOException {

        URL oracle = new URL("https://appcenter.intuit.com/connect/oauth2?"
                + "client_id=Q0MSoIoxKUgAguLFkgVrVXXgyXBJI3xM7WCdtt2mDHsGbc546e"
                + "response_type=code&\n"
                + "scope=com.intuit.quickbooks.accounting&\n"
                + "redirect_uri=https://developer.intuit.com/v2/OAuth2Playground/RedirectUrl&"
                + "state=security_token%3D138r5719ru3e1%26url%3Dhttps://developer.intuit.com/v2/OAuth2Playground/RedirectUrl&");
        BufferedReader in = new BufferedReader(
                new InputStreamReader(oracle.openStream()));

        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            System.out.println(inputLine);
        }
        in.close();

        return null;

//Sandbox Base URL: https://sandbox-quickbooks.api.intuit.com
//Production Base URL: https://quickbooks.api.intuit.com
//Operation: GET /v3/company/<realmID>/reports/AccountList?<name>=<value>[&...]
//Accept type: application/json
        // Intuit.Ipp.Data.Qbo.CompanyMetaData actualCompanyMetaData =commonService.FindById(new Intuit.Ipp.Data.Qbo.CompanyMetaData();
        //string cpnyname = actualCompanyMetaData.QBNRegisteredCompanyName;   
    }
}
