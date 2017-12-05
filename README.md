OAuth2
## Instalation
- `git clone https://github.com/sebivenlo/OAuth2.git`
- `Open the OAuth2_Workshop_exercise project in netbeans`
- `Install maven if needed`
- `Install spring if needed`
- `Press control 6 for exercises and start. A guidence is included underneath`

## OAuth2_Workshop_exercise
``` 
Try to start your application and see if you can login with facebook.
``` 
``` 
we are going to pick the @EnableOAuth2Sso annotation
``` 
- there are 2 features behind the @EnableOAuth2Sso annotation:
	- the OAuth2 client – is reusable, so you can also use it to interact with the OAuth2 resources that your Authorization server provides(in this case facebook).
	- The authentication – aligns your app with the rest of spring security, so once the dance with facebook is over your app behaves exactly like any other secure spring app.
	
- First step is to remove the @EnableOauth2Sso annotation and replace it as a OAuth2client.(TODO 1.1)

- Once that is done you have to create some stuff that will be usefull later on.
	- 1 create an OAuth2ClientContext, This is used to set up the authentication filter for the client later on.(TODO 1.2)
	- 2 Create a bean for the client registration with facebook, Hint in slides.(TODO 1.3)
	- 3 Create a bean to complete the authentication since it needs to know where the user info endpoint is in facebook, hint in slides(TODO 1.4)
- 4 and use these items to build an authentication filter method. This might be a bit hard to understand so first try to figure it out yourself and otherwise you can look in the solution project(TODO 1.5).
- Tip: look closely in the required paramaters or look at the imports that are still unused.
	- `new OAuth2RestTemplate()`
	- `set resttemplate for filter`
	- `new UserInfoTokenServices();`
	- `set rest template for tokenservice`
	- `set tokenservice for filter`


- Since we declared both these static dataobjects(facebook() and facebookResrouce()) as a @Bean decorated as an @ConfigurationPorperties it means you can also convert the application.yml resource file as a new format. Security.oauth2 can be removed and it can be configured as facebook(TODO 1.6)

- Since the previous application also used this annotation in the html file we need to change it here as well.(TODO 1.7)

- Lastly – handling the redirects
- we need to explicitly support the redirects from our app to facebook. This is handled in Spring Oauth2 with a servlet filter. This filter is already available because we used the @EnableOAuth2Client. To wire this all up, all we need is a FilterRegistartionBean.(TODO 1.9)
``` 
Fire up your application and see if it works! you can visit the application at "localhost:8080"
``` 