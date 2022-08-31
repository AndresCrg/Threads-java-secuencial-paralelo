package models;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebManager{

	private static final Proxy PROXY = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("172.16.0.73", 8080));
	private ChangeImage changeImage;
	private HashSet<String> imagesList;
	private static final String WEB = "https://alpha.wallhaven.cc/search?q=";

	public WebManager() {
		imagesList = new HashSet<>();
		changeImage = new ChangeImage();
	}

	public HashSet<String> read(String data) throws IOException {
		URL url = new URL(WEB + data);
		URLConnection hc = url.openConnection();
		hc.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		BufferedReader in = new BufferedReader(
				new InputStreamReader(hc.getInputStream()));
		String webText = "";
		String inputLine;
		while ((inputLine = in.readLine()) != null)
			webText += inputLine + "\n";
		in.close();
		Pattern pattern = Pattern.compile("(src=\"https://alpha.wallhaven.cc/wallpapers/thumb/small/)([a-zA-Z\\d/-])*(\\.[a-zA-Z]{3})");
		Matcher matcher = pattern.matcher(webText);
		while(matcher.find()) {
			System.out.println(matcher.group(0));
			imagesList.add(matcher.group(0).substring(5));
		}
		return imagesList;
	}

	public void copy(String img) throws IOException {
		URL website = new URL(img);
		URLConnection hc = website.openConnection(PROXY);
		hc.setRequestProperty("User-Agent", "Mozilla/5.0 (Macintosh; U; Intel Mac OS X 10.4; en-US; rv:1.9.2.2) Gecko/20100316 Firefox/3.6.2");
		try (InputStream in = hc.getInputStream()) {
			Files.copy(in, Paths.get("./dataBase", 
					new File(img).getName()), StandardCopyOption.REPLACE_EXISTING);
		}
	}

	public void changeImageColor(String image){
		 changeImage.change(image);
	}

	public HashSet<String> getImagesList() {
		return imagesList;
	}

	public HashSet<String>  chargueImageBase(){
		File f = new File("./dataBase/");
		File[] ficheros = f.listFiles();
		for (int i = 0; i < ficheros.length; i++) {
			imagesList.add(ficheros[i].getPath());
		}
		return imagesList;
	}
	
	public HashSet<String>  chargueImageBaseBM(){
		File f = new File("./dataBaseB&M/");
		File[] ficheros = f.listFiles();
		for (int i = 0; i < ficheros.length; i++) {
			imagesList.add(ficheros[i].getPath());
		}
		return imagesList;
	}
}