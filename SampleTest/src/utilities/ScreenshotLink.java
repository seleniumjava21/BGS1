package utilities;

import com.aventstack.extentreports.markuputils.Markup;

public class ScreenshotLink implements Markup {
	private String filepath;
	private String linkName = "ScreenShot";
	public ScreenshotLink(String filePath){
		this.filepath = filePath;
	}
	
	public ScreenshotLink(String filePath,String lnk){
		this.filepath = filePath;
		this.linkName = lnk;
	}
	
	@Override
	public String getMarkup() {
		 final String htmlTag = "<a href='"+filepath+"' target='_new'style = 'color:blue;'><u>"+linkName+"</u></a>";
		 //final String htmlTag = "<a href='"+filepath+"' target='_new'style = 'color:blue;'></a>";
        return htmlTag;
	}

}