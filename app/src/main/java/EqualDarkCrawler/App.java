/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package EqualDarkCrawler;

import EqualDarkCrawler.crawler.ListCrawler;
import EqualDarkCrawler.crawler.ProductCrawler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class App {
    public static List<String> getProductsURL() {
        Map<String, ListCrawler> crawlers = new HashMap<>();
        crawlers.put("killstar", new EqualDarkCrawler.brands.killstar.ListCrawler());

        Scanner scan = new Scanner(System.in);
        System.out.print("브랜드를 입력해주세요. [killstar]: ");
        String brand = scan.nextLine();

        System.out.print("크롤링 할 주소를 입력해주세요: ");
        String targetURL = scan.nextLine();

        scan.close();

        ListCrawler listCrawler = crawlers.get(brand);

        try {
            listCrawler.setTargetURL(targetURL);
        } catch (Exception e) {
            System.out.println("target url parse failed");
            System.exit(1);
        }

        try {
            return listCrawler.getProductsURL();
        } catch (Exception e) {
            System.out.println("get products url failed");
            System.exit(1);
        }
        return null;
    }

    public static void getProduct() {
        Map<String, ProductCrawler> crawlers = new HashMap<>();
        crawlers.put("killstar", new EqualDarkCrawler.brands.killstar.ProductCrawler());

        Scanner scan = new Scanner(System.in);
        System.out.print("브랜드를 입력해주세요. [killstar]: ");
        String brand = scan.nextLine();

        System.out.print("크롤링 할 주소를 입력해주세요: ");
        String targetURL = scan.nextLine();

        scan.close();

        ProductCrawler productCrawler = crawlers.get(brand);

        try {
            productCrawler.setTargetURL(targetURL);
        } catch (Exception e) {
            System.out.println("target url parse failed");
            System.exit(1);
        }

        boolean isValidPage = productCrawler.isValidPage();
        if (!isValidPage) {
            System.out.println("invalid page");
            System.exit(0);
        }

        boolean isSoldOut = productCrawler.isSoldOut();
        String name = productCrawler.getName();
        float price = productCrawler.getPrice();
        float salePrice = productCrawler.getSalePrice();

        System.out.printf("soldout : %b\n", isSoldOut);
        System.out.printf("name : %s\n", name);
        System.out.printf("price : %f\n", price);
        System.out.printf("sale price : %f\n", salePrice);
    }

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.println("1. 제품 리스트 크롤링");
        System.out.println("2. 제품 정보 크롤링");
        System.out.print("번호를 선택하세요: ");
        String selected = scan.nextLine();

        if (selected.equals("1")) {
            List<String> productsURL = getProductsURL();
            System.out.println(productsURL);
        } else if (selected.equals("2")) {
            getProduct();
        }
    }
}
