# Java API for the OBS
A Java API for communicating with OBS.
Currently supporting [OBS-Websocket](https://github.com/Palakis/obs-websocket) with Java 11+ Websocket client and Jackson.

[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=harm27_obs-java-client&metric=alert_status)](https://sonarcloud.io/dashboard?id=harm27_obs-java-client)
![Master Build](https://github.com/harm27/obs-java-client/workflows/Master%20Build/badge.svg)

# News
Version 1.3 is released and rebuilds the requests en events to be generated based on obs-websocket protocol definition.

Also it supports all requests that are part of OBS v4.9.0. 

Version 1.4 is targeted to improve the properties generation.

# Download options
To be able to use this project you can either download the jars or use the maven dependency. Nightly builds are only available as maven dependency.

If you want to use the jar directly, you can download it at https://github.com/harm27/obs-java-client/releases. There you can find the uber/shaded-jar version with no further dependencies.

If you want to use Maven, you can find the latest jars in https://github.com/harm27/obs-java-client/packages. There you can find two versions, 1 uber/shaded-jar with all dependencies embeded and 1 jar with the dependencies through maven.

# Documentation
At [harm27.github.io/obs-java-client](https://harm27.github.io/obs-java-client) you can find the following information:
- Javadoc for master and every released version
- Releasenotes for every released version

# SonarCloud codequality
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=harm27_obs-java-client&metric=ncloc)](https://sonarcloud.io/dashboard?id=harm27_obs-java-client)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=harm27_obs-java-client&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=harm27_obs-java-client)


[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=harm27_obs-java-client&metric=bugs)](https://sonarcloud.io/dashboard?id=harm27_obs-java-client)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=harm27_obs-java-client&metric=code_smells)](https://sonarcloud.io/dashboard?id=harm27_obs-java-client)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=harm27_obs-java-client&metric=sqale_index)](https://sonarcloud.io/dashboard?id=harm27_obs-java-client)

[![Maintainability Rating](https://sonarcloud.io/api/project_badges/measure?project=harm27_obs-java-client&metric=sqale_rating)](https://sonarcloud.io/dashboard?id=harm27_obs-java-client)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=harm27_obs-java-client&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=harm27_obs-java-client)

[![Security Rating](https://sonarcloud.io/api/project_badges/measure?project=harm27_obs-java-client&metric=security_rating)](https://sonarcloud.io/dashboard?id=harm27_obs-java-client)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=harm27_obs-java-client&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=harm27_obs-java-client)
