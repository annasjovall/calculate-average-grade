package avg;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.encryption.InvalidPasswordException;
import org.apache.pdfbox.text.PDFTextStripper;

public class CalculateAverage {

	public static void main(String[] args) {
		// Supress output from pdfbox
		java.util.logging.Logger.getLogger("org.apache.pdfbox").setLevel(java.util.logging.Level.SEVERE);

		String text = "";

		try {
			File file = new File(args[0]);
			PDDocument document = PDDocument.load(file);
			PDFTextStripper pdfStripper = new PDFTextStripper();
			text = pdfStripper.getText(document);
			document.close();
		} catch (NullPointerException e) {
			System.out.println("Please add a valid file path");
		} catch (InvalidPasswordException e) {
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error opening file");
			e.printStackTrace();
		}

		int i = 0;
		double nominator = 0;
		double denominator = 0;

		while (i + 6 < text.length()) {
			if (text.substring(i).startsWith("Credited education"))
				break;
			String course_code = text.substring(i, i + 6);

			if (course_code.matches("[A-Z]{3}[A-Z:0-9][0-9]{2}")) {
				int j = i + 6;

				while (text.charAt(j) != '.')
					j++;

				String points = text.substring(j - 2, j + 2);
				if (!points.matches("\\d\\d[.]\\d"))
					points = text.substring(j - 1, j + 2);

				j += 2;

				double hp = Double.parseDouble(points);

				String gradeChar = text.substring(j + 5, j + 6);
				System.out.println("Course Code: " + course_code + ", Points: " + hp + ", Grade: " + gradeChar);

				if (gradeChar.matches("\\d")) {
					int grade = Integer.parseInt(gradeChar);
					denominator += hp;
					nominator += hp * grade;
				}
				i = j + 4;
			}
			i++;
		}

		if (denominator > 0)
			System.out.println("\n----Average: " + nominator / denominator + "----");
		else
			System.out.println("Error calculating average");
	}
}
