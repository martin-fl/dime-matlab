# Matlab-DIME Integration

This repo contains:
- a server (under `matlab_integration/matlab_server`) capable of handling matlab
  functions calls sent as JSON
- a client (under `matlab_integration/matlab_client`) meant to show the
  basic capabilities of the server
- a common library (under `matlab_integration/matlab_common`) to write matlab
  functions call in Java, compatible with the server, meant for use in DIME
  SIBS.
- a DIME project (under `matlab_showcase`), equipped with a Native SIBS library
  as a frontend for a client to a matlab-java server, to demonstrate the basic
  usage of Matlab in DIME.

## Building

- To build the java libraries, refer to `matlab_integration/README.md`.
- To build the DIME project, refer to the [DIME wiki](https://scce.gitlab.io/dime/content/user-guide/development-with-docker.html).

## Extending the SIBs library

To extend the functionnalities of the matlab integration, there are several
things to do:
- Add the matlab code you wish to use in DIME to the
  `matlab_integration/matlab_code` directory.
- Add a static java function to call that function in the
  `info.scce.dime.matlab.client.Bridge` class (refer to the other functions in
  the file).
- Add a sib entry in the `matlab_showcase/dime-models/matlab.sibs` file (refer
  to the other sibs).

After that you should be good to go (modulo compilation errors :-)).

## TODO

- currently the `matlab_common` library is copy pasted from
  `matlab_integration/` to `matlab_showcase/dependencies/` and not shared as a
  jar because maven is a headache
- exceptions are barely handled on the client-side
- still haven't figured out how to ergonomically use Nd arrays in DIME
- graphs, anyone ?
