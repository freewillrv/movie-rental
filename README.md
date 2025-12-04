# movie-rental
Spring Boot Example with Elastic, MySQL, Kafka, Spring Security, Hibernate.

## Requirements
* Movie Rental Service for shops.
* Should provide common Movie DB for ease of onboarding.
* Should provide Mechanism to onboard users, movie catalogue, fast search for catalogue.
* System should be multi tenanted. (Future iteration)
* Support Elastic search
* Support API for admin for store onboarding APIs.
* Support Authz and Authentication via Spring.


## Design

API Design

```
/auth/login --> Used to Login to system

Response

{
 "accessToken": "jwtToken",
 "refreshToken": "refreshToken",
 "validTill" : "refreshTokenValidTill"
}

Request
{
 "userName": "name",
 "password": "password"
}

Implementation Details.

Need a user table.

user
id, userName, email, givenName, lastName, lastLogin, password

id primary key - system generated
password should be hashed
    --> Hash with salt store that
    --> {Hash-Algo}<actual_hash_value>
Add Unique key for userName, email
No index needed for password as we will use PBKDF2

Private Key for Auth Service
To generate the tokens, access and refresh
    claims --> add in each token.
    refresh token -- expiry.
    
Open ssl key pair generate.

Keep on local machine where the docker will run and share the tokens  

```

/auth/token/refresh
/auth/token/invalidate