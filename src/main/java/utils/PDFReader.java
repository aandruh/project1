package utils;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.testng.annotations.Test;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class PDFReader {

    public static String getPDFContent(String pdfLink) throws IOException {
//URL url = new URL("https://files.staging-bemakers.com/files/eyJfcmFpbHMiOnsibWVzc2FnZSI6IkJBaHBBdlVRIiwiZXhwIjoiMjAyMy0wNC0xNFQxODoyOTo0NS45NDYwMDBaIiwicHVyIjoiYmxvYl9pZCJ9fQ==--b0632c521069715ba44f1d1be88012469305866b");
        URL url = new URL(pdfLink);

        InputStream is = url.openStream();
        BufferedInputStream fileParse = new BufferedInputStream(is);
        PDDocument document = null;

        document = PDDocument.load(fileParse);
        String pdfContent = new PDFTextStripper().getText(document);
//        System.out.println(pdfContent);
        return pdfContent;


    }
}
