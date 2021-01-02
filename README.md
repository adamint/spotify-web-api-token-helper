# spotify-web-api-kotlin user authorization token generator helper

This helper library lets you painlessly generate a user (client) authorization token, for use in testing or otherwise.

**Required**:

1. You must create an application using
   the [Spotify developer dashboard](https://developer.spotify.com/dashboard/applications).

2. You need to add a valid redirect URI to the application (this does not have to be a valid web server or one that you
   own. For example, I use https://apple.com).

3. You need to add three environment variables:
    - `SPOTIFY_CLIENT_ID`: the application client id
    - `SPOTIFY_CLIENT_SECRET`: the application client secret
    - `SPOTIFY_REDIRECT_URI`: the redirect URI you added. protocol (http or https) is required

4. ??

5. Profit


To run this library, you can do one of two things:

- Run `gradle installDist`, and in the repository root please run
  `./build/install/spotify-web-api-token-helper/bin/spotify-web-api-token-helper` (if UNIX, add .bat if Windows)

- Or, open this repository using IntelliJ IDEA and click the green "run" button in `SpotifyTokenHelper.kt` 
  (note that the environment variables must either be on the system path or added in the run configuration)