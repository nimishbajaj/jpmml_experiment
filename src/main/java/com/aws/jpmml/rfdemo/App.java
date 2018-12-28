package com.aws.jpmml.rfdemo;
//import com. package org.jpmml.evaluator;


import com.amazonaws.auth.EnvironmentVariableCredentialsProvider;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import org.dmg.pmml.FieldName;
import org.dmg.pmml.IOUtil;
import org.dmg.pmml.MiningModel;
import org.dmg.pmml.PMML;
import org.jpmml.evaluator.FieldValue;
import org.jpmml.evaluator.MiningModelEvaluator;
import org.jpmml.evaluator.ModelEvaluator;
import org.jpmml.evaluator.ProbabilityClassificationMap;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

import static java.lang.System.out;

import org.dmg.pmml.IOUtil;
import org.dmg.pmml.*;
//import org.shaded.pmml.tree.TreeModel;

/*
 *
 */
public class App implements RequestHandler<IrisDataRequest, IrisDataResponse> {
    
	private static String bucketName = "sagemaker-verizon";
	private static String key = "AWSLambda/RFTravelPMML/RandomForestITP.pmml";
	//private InputStream fileName;


	public IrisDataResponse handleRequest(IrisDataRequest request, Context context) {
        

	context.getLogger().log("Input: " + request.toString());
	
       try{	
	

	//connect to S3
	AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withCredentials(new EnvironmentVariableCredentialsProvider()).build();
			S3Object object = s3Client.getObject(new GetObjectRequest(bucketName, key));
			InputStream objectData = object.getObjectContent();
			PMML pmml = createPMMLfromFile(objectData);
			// Process the objectData stream.
			objectData.close();
	//pass pmml to modelevaluator
        ModelEvaluator<MiningModel> modelEvaluator = new MiningModelEvaluator(pmml);
        printArgumentsOfModel(context,modelEvaluator);

       

	
            Map<FieldName, FieldValue> arguments = readArgumentsFromLine(request, modelEvaluator);

           // modelEvaluator.verify();

            Map<FieldName, ?> results = modelEvaluator.evaluate(arguments);
		

            FieldName targetName = modelEvaluator.getTargetField();
            Object targetValue = results.get(targetName);

            ProbabilityClassificationMap nodeMap = (ProbabilityClassificationMap) targetValue;
//=------------------------------------------------------------------------------------------------------------//
	    out.println("\n% 'Probability (1)':" + nodeMap.getProbability("probability(1)"));
	    out.println("\n% 'Probability (2)':" + nodeMap.getProbability("probability(2)"));
	    out.println("\n% 'Probability (3)':" + nodeMap.getProbability("probability(3)"));
	    out.println("\n% 'Probability (4)':" + nodeMap.getProbability("probability(4)"));
	   // out.println(" ==Result:" + nodeMap.getResult() + "\n");

		   
	    IrisDataResponse response = new IrisDataResponse ();
           
	    response.setClusterId((String) nodeMap.getResult());

	    //return (IrisDataResponse) nodeMap.getResult();
	    return response;
        }

	catch(Exception e){
			e.printStackTrace();
			// TODO Auto-generated catch block
			context.getLogger().log("error: " + e.getMessage());
			return null;
	}
	//return null;
	 
    }


    public Map<FieldName, FieldValue> readArgumentsFromLine(IrisDataRequest request, ModelEvaluator<MiningModel> modelEvaluator) {
        Map<FieldName, FieldValue> arguments = new LinkedHashMap<FieldName, FieldValue>();
        //String[] lineArgs = line.split(",");


	
     	FieldValue device_memory = modelEvaluator.prepare(new FieldName("device_memory"),
				request.device_memory.isEmpty() ? 0 : request.device_memory);
		FieldValue data_plan = modelEvaluator.prepare(new FieldName("data_plan"),
				request.data_plan.isEmpty() ? 0 : request.data_plan);
		FieldValue active_app_user = modelEvaluator.prepare(new FieldName("active_app_user"),
				request.active_app_user.isEmpty() ? 0 : request.active_app_user);
		FieldValue international_travel_plan_opted = modelEvaluator.prepare(new FieldName("international_travel_plan_opted"),
				request.international_travel_plan_opted.isEmpty() ? 0 : request.international_travel_plan_opted);
		FieldValue num_visits_2_day = modelEvaluator.prepare(new FieldName("num_visits_2_day"),
				request.num_visits_2_day.isEmpty() ? 0 : request.num_visits_2_day);
		FieldValue num_pages_2_days = modelEvaluator.prepare(new FieldName("num_pages_2_days"),
				request.num_pages_2_days.isEmpty() ? 0 : request.num_pages_2_days);
		FieldValue num_days_visited_1_month = modelEvaluator.prepare(new FieldName("num_days_visited_1_month"),
				request.num_days_visited_1_month.isEmpty() ? 0 : request.num_days_visited_1_month);
		FieldValue num_calls_15_days = modelEvaluator.prepare(new FieldName("num_calls_15_days"),
				request.num_calls_15_days.isEmpty() ? 0 : request.num_calls_15_days);
		FieldValue total_call_duration_last_1_month = modelEvaluator.prepare(new FieldName("total_call_duration_last_1_month"),
				request.total_call_duration_last_1_month.isEmpty() ? 0 : request.total_call_duration_last_1_month);
		FieldValue response_rate_last_6_months = modelEvaluator.prepare(new FieldName("response_rate_last_6_months"),
				request.response_rate_last_6_months.isEmpty() ? 0 : request.response_rate_last_6_months);
		FieldValue ads_sent_1_month = modelEvaluator.prepare(new FieldName("ads_sent_1_month"),
				request.ads_sent_1_month.isEmpty() ? 0 : request.ads_sent_1_month);
		FieldValue total_data_used_1_yr = modelEvaluator.prepare(new FieldName("total_data_used_1_yr"),
				request.total_data_used_1_yr.isEmpty() ? 0 : request.total_data_used_1_yr);
		FieldValue days_since_last_applogin = modelEvaluator.prepare(new FieldName("days_since_last_applogin"),
				request.days_since_last_applogin.isEmpty() ? 0 : request.days_since_last_applogin);
		FieldValue days_since_activation = modelEvaluator.prepare(new FieldName("days_since_activation"),
				request.days_since_activation.isEmpty() ? 0 : request.days_since_activation);

		arguments.put(new FieldName("device_memory"), device_memory);
		arguments.put(new FieldName("data_plan"), data_plan);
		arguments.put(new FieldName("active_app_user"), active_app_user);
		arguments.put(new FieldName("international_travel_plan_opted"), international_travel_plan_opted);
		arguments.put(new FieldName("num_visits_2_day"), num_visits_2_day);
		arguments.put(new FieldName("num_pages_2_days"), num_pages_2_days);
		arguments.put(new FieldName("num_days_visited_1_month"), num_days_visited_1_month);
		arguments.put(new FieldName("num_calls_15_days"), num_calls_15_days);
		arguments.put(new FieldName("total_call_duration_last_1_month"), total_call_duration_last_1_month);
		arguments.put(new FieldName("response_rate_last_6_months"), response_rate_last_6_months);
		arguments.put(new FieldName("ads_sent_1_month"), ads_sent_1_month);
		arguments.put(new FieldName("total_data_used_1_yr"), total_data_used_1_yr);
		arguments.put(new FieldName("days_since_last_applogin"), days_since_last_applogin);
		arguments.put(new FieldName("days_since_activation"), days_since_activation);

		return arguments;

        
    }

    public void printArgumentsOfModel(Context context,ModelEvaluator<MiningModel> modelEvaluator){
        context.getLogger().log("### Active Fields of Model ####");
        for (FieldName fieldName : modelEvaluator.getActiveFields()) {
			context.getLogger().log("Field Name: " + fieldName);
        }
    }



    public PMML createPMMLfromFile(InputStream fileName) throws Exception {
		

		DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(fileName);

		Node pmmlNode = doc.getElementsByTagName("PMML").item(0);
		NamedNodeMap attr = pmmlNode.getAttributes();
		Node nodeAttr = attr.getNamedItem("xmlns");
		nodeAttr.setTextContent("http://www.dmg.org/PMML-4_3");
		((Element)pmmlNode).setAttribute("version","4.3");

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		StringWriter writer = new StringWriter();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(writer);
		transformer.transform(source, result);

		String pmmlString = writer.toString();
		InputStream stream = new ByteArrayInputStream(pmmlString.getBytes(StandardCharsets.UTF_8));
		/*
		InputSource source = new InputSource(stream);
		SAXSource transformedSource = ImportFilter.apply(stream);
		*/

		//PMML pmml = JAXBUtil.unmarshalPMML ((Source) stream);

		//return pmml;
		return IOUtil.unmarshal(stream);

    }
}

