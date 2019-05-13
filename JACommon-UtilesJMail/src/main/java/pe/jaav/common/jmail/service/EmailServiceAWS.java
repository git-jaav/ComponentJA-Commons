package pe.jaav.common.jmail.service;

import java.util.List;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

import pe.jaav.common.jmail.model.Email;
import pe.jaav.common.jmail.util.Utiles;

public class EmailServiceAWS {	
	
	private Regions regionDefault;
	
	private static final String CODIFICACION_UTF_8 = "UTF-8";
		
	private String from;
	private String to;	
	private List<String> toList;
	
	private String cc;
	private List<String> ccList;

	private String bcc;
	private List<String> bccList;

	private String subject;
	private String htmlBody;
	private String textBody;
	
	
	public void setParametrosEmail(Email objMail){
		this.subject = objMail.getSubject();
		this.htmlBody = objMail.getBody();
		this.textBody = objMail.getTextBody();
		this.from = objMail.getCORREO_ACCOUNT();
		this.toList = objMail.getListCorreoDestinos();
		this.to = objMail.getCorreoDestino();
		this.cc = objMail.getCorreoDestinoCC();
		this.ccList = objMail.getListCorreoDestinosBCC();
		this.bcc = objMail.getCorreoDestinoCC();
		this.bccList = objMail.getListCorreoDestinosBCC();	
		
		this.regionDefault = Regions.US_EAST_1;
	}
	
	public boolean sendEmail() throws Exception {
		boolean result = false;		
    	if(Utiles.noEsVacio(from) && (Utiles.noEsVacio(to) || Utiles.noEsVacio(toList)) && (
    			Utiles.noEsVacio(htmlBody) || Utiles.noEsVacio(textBody))) {
    		
    	Destination  destinosMail = new Destination();
    	if(Utiles.noEsVacio(toList)) {
    		destinosMail.withToAddresses(toList);	
    	}else {
    		destinosMail.withToAddresses(to);
    	}    	
    	
    	if(Utiles.noEsVacio(ccList)) {
    		destinosMail.withCcAddresses(ccList);	
    	}else {
    		destinosMail.withCcAddresses(cc);
    	}
    	
    	if(Utiles.noEsVacio(bccList)) {
    		destinosMail.withBccAddresses(bccList);	
    	}else {
    		destinosMail.withBccAddresses(bcc);
    	}
    	
  	      AmazonSimpleEmailService client = 
  		          AmazonSimpleEmailServiceClientBuilder.standard()
  		          // Replace US_WEST_2 with the AWS Region you're using for
  		          // Amazon SES.
  		            .withRegion(regionDefault).build();
  		      SendEmailRequest request = new SendEmailRequest()
  		          .withDestination(
  		              new Destination().withToAddresses(Utiles.noEsVacio(toList)?toList:toList)
  		              )
  		          .withMessage(new Message()
  		              .withBody(new Body()
  		                  .withHtml(new Content()
  		                      .withCharset(CODIFICACION_UTF_8).withData(htmlBody))
  		                  .withText(new Content()
  		                      .withCharset(CODIFICACION_UTF_8).withData(textBody)))
  		              .withSubject(new Content()
  		                  .withCharset(CODIFICACION_UTF_8).withData(subject)))
  		          .withSource(from)
  		          ;
  		          // Comment or remove the next line if you are not using a
  		          // configuration set
  		          //.withConfigurationSetName(CONFIGSET);
  		      client.sendEmail(request);
  		    result = true;
    	}
    	return result;
	}
}
