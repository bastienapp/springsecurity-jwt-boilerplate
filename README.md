# Spring Security JPA UserDetailsService

## resources/application.properties

Change the `jwt.secret` to a long and random character string.

## JpaSecurityApplication

One admin, one user and one blog article are created on startup.

## SecurityConfig

The `jwt.secret` value is loaded from the application.properties file.

`filterChain`: Authorize the `/login` endpoint for everyone, and you'll need to be connected for all other endpoints.

## AuthController

Add a `POST /login` endpoint, which send back the Json Web Token in response.

This endpoint accepts a `LoginDto` as JSON request body:

```json
{
  "email": "user@example.com",
  "password": "password"
}
```

Response should looks like this:

```
eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJzZWxmIiwic3ViIjoidXNlciIsImV4cCI6MTY2OTkwNDQzOSwiaWF0IjoxNjY5OTAwODM5LCJzY29wZSI6IlVTRVIifQ.DD9WHGGi94wtgthopr-Y9DyFGjgnvsmJYnIrdIao0Es
```

## PostController

Add `GET /api/posts` and `GET /api/posts/{id}` endpoints where you need to be signed in as a user.

If you call these endpoints without the JWT, you'll get a 401 error (Unauthorized).

In Postman, you need to send the JWT:

* In the `Authorization` tab
* Select `Bearer token`
* In the `Token` field, paste the token from the previous `POST /login` response

## TokenService

Set how the JWT Web Token will be encoded and what it should contains.

Here the expiration date is one hour.

## JpaUserDetailsService

* loadUserByUsername: load the user in database from its email then converts it to `SecurityUser`

## model/SecurityUser

Implements `UserDetails` from Spring Security