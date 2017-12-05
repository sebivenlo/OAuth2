OAuth2
## Instalation
`git clone https://github.com/sebivenlo/OAuth2.git`
`Open the OAuth2_Workshop_exercise project in netbeans`
`Install maven if needed`
`Install spring if needed`
`Press control 6 for exercises and start. A guidence is included underneath`

## OAuth2_Workshop_exercise
First show how @EnableOAuth2Sso works when just putting it there:
This is basically adding the annotation and setting up the configuration and explain how this works 
Then tell them that we are going to pick the @EnableOAuth2Sso annotation apart and let them work on the manual project.
And after that give them a diagram with the steps not filled in and then let them try and fill in the diagram. After that give an explanation about it as a sort of final conclusion and finish the 
@EnableOAuth2Sso explanation
there are 2 features behind the @EnableOAuth2Sso annotation: 
the OAuth2 client – is reusable, so you can also use it to interact with the OAuth2 resources that your Authorization server provides(in this case facebook).
The authentication – aligns your app with the rest of spring security, so once the dance with facebook is over your app behaves exactly like any other secure spring app.
First step is to remove the @EnableOauth2Sso annotation and replace it as a OAuth2client.
Once that is done you have to create some stuff that will be usefull later on.
1 inject an OAuth2ClientContext
2 Create a bean for the client registration with facebook !!has to be explained in slides with example!
3 Create a bean to complete the authentication  since it needs to know where the user info endpoint is in facebook !!has to be explained in slides with example!
4 and use these items to build an authentication filter method.
Since we declared both these static dataobjects(facebook() and facebookResrouce()) as a @Bean decorated as an @ConfigurationPorperties it means you can also convert the application.yml resource file as a new format. Security.oauth2 can be removed and it can be configured as facebook
Since the previous application also used this annotation in the html file we need to change it here as well.
Lastly – handling the redirects
we need to explicitly support the redirects from our app to facebook. This is handles in Spring Oauth2 with a servlet filter. This filter is already available because we used the @EnableOAuth2Client. To wire this all up, all we need is a FilterRegistartionBean.
