package net.n1books.dev.nlu;

import org.junit.Test;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.ConceptsOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;

public class NLUTest01 {

	@Test
	public void testNLU() {
		NaturalLanguageUnderstanding service = 
				new NaturalLanguageUnderstanding(NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
						 "cb31dbfa-5515-406a-a113-65c0bfb1d9c4",
						 "zFRBB3mZodt0");
		
		String url = "http://entertain.naver.com/read?oid=076&aid=0003227819";
		ConceptsOptions concepts = new ConceptsOptions.Builder().limit(20).build();	
		
		Features features = new Features.Builder().concepts(concepts).build();
		AnalyzeOptions paramters =
				new AnalyzeOptions.Builder().url(url).features(features).build();	
		
		AnalysisResults response = service.analyze(paramters).execute();	
		System.out.println(response);
	}
}
