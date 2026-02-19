<!--
  Licensed to the Apache Software Foundation (ASF) under one
  or more contributor license agreements.  See the NOTICE file
  distributed with this work for additional information
  regarding copyright ownership.  The ASF licenses this file
  to you under the Apache License, Version 2.0 (the
  "License"); you may not use this file except in compliance
  with the License.  You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing,
  software distributed under the License is distributed on an
  "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
  KIND, either express or implied.  See the License for the
  specific language governing permissions and limitations
  under the License.
-->

# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Apache Cocoon 2.3.x is a web application framework for XML processing and publishing. It uses a pipeline-based architecture where requests flow through generators, transformers, and serializers defined in sitemaps.

## Quick Start

```bash
# 1. Build the entire project (includes all blocks)
./build.sh install

# 2. Start the Cocoon webapp
./cocoon.sh

# 3. Open browser to http://localhost:8888/
```

If step 1 fails with missing `cocoon-rcl`, build tools first:
```bash
cd tools && mvn clean install && cd .. && ./build.sh install
```

## Build System

This is a multi-module Maven project with the following structure:

- **core/** - Core framework modules (pipeline, sitemap, configuration, servlet-service, etc.)
- **blocks/** - Optional feature modules (50+ blocks for AJAX, forms, FOP, databases, etc.)
- **tools/** - Build tools, Maven plugins, and archetypes
- **commons/** - Shared legal documents
- **dists/** - Distribution assemblies

### Prerequisites

- JDK 21 (required for Jakarta EE 9+ compatibility)
- Maven 3 (Maven wrapper `mvnw`/`mvnw.cmd` is included - no separate Maven installation required)

### Build Commands

Build the project using the `build.sh` wrapper script (Unix/Mac):

```bash
# Full build with all blocks
./build.sh install

# Build without tests
./build.sh notest install

# Clean build
./build.sh clean install

# Alternative: Use Maven directly with allblocks profile
mvn -P allblocks install

# Windows users: Use Maven wrapper with allblocks profile
mvnw.cmd -P allblocks install
```

**Note for Windows users**: The `.sh` scripts are bash scripts that work in Git Bash or WSL. Alternatively, use `mvnw.cmd` with the `-P allblocks` profile directly.

The `build.sh` script automatically activates the `allblocks` Maven profile, which includes all optional blocks. Without this profile, **only core blocks are built**:

**Core blocks (always built):**
- cocoon-ajax, cocoon-apples, cocoon-core-sample, cocoon-flowscript, cocoon-forms, cocoon-it
- cocoon-linkrewriter, cocoon-template, cocoon-samples-style, cocoon-batik, cocoon-welcome

**Optional blocks (require allblocks profile):**
- cocoon-asciiart, cocoon-auth, cocoon-authentication-fw, cocoon-bsf, cocoon-captcha
- cocoon-cron, cocoon-databases, cocoon-eventcache, cocoon-faces, cocoon-fop, cocoon-html
- cocoon-itext, cocoon-javaflow, cocoon-jcr, cocoon-jfor, cocoon-jms, cocoon-jsp, cocoon-lucene
- cocoon-mail, cocoon-midi, cocoon-naming, cocoon-ojb, cocoon-petstore, cocoon-poi, cocoon-portal
- cocoon-profiler, cocoon-proxy, cocoon-python, cocoon-qdox, cocoon-querybean, cocoon-repository
- cocoon-scratchpad, cocoon-serializers, cocoon-session-fw, cocoon-slide, cocoon-slop, cocoon-stx
- cocoon-taglib, cocoon-tour, cocoon-validation, cocoon-velocity, cocoon-web3, cocoon-webdav, cocoon-xsp

**Note:** cocoon-axis is excluded from default builds (deprecated). Build with `-DincludeAxis` if needed.

See `blocks/pom.xml` for the definitive list.

**Important**: If you encounter build failures due to missing `cocoon-rcl` artifact, first build the tools directory:
```bash
cd tools
mvn clean install
cd ..
```

The `tools/` directory contains:
- **cocoon-rcl** - Cocoon Resource ClassLoader (required for builds)
- **archetypes** - Maven archetypes for creating new Cocoon projects
- **cocoon-it-fw** - Integration test framework
- Maven plugins for Cocoon-specific build tasks

### Running the Application

Start Cocoon with Jetty (requires a completed `./build.sh install` first — `cocoon.sh` serves from `target/cocoon-webapp`):

```bash
./cocoon.sh
```

The application will be available at http://localhost:8888/

To run in debug mode (JVM debug port 5005):

```bash
./cocoon.sh debug
```

The `cocoon.sh` script runs `mvn jetty:run` in the `core/cocoon-webapp` directory.

### Building Documentation

Generate and deploy documentation locally:

```bash
./build-docs.sh
```

This builds the site documentation with Maven reports.

## Architecture

### Pipeline Processing

Cocoon uses a pipeline architecture for request processing:
- **Generators** - Create SAX events from various sources
- **Transformers** - Process SAX events (XSLT, etc.)
- **Serializers** - Convert SAX events to output formats
- **Readers** - Direct byte streaming (bypass SAX pipeline)

### Sitemap

The sitemap (`sitemap.xmap`) is the core configuration file that maps URLs to pipelines. Sitemaps are hierarchical and can be found in:
- Block implementations: `blocks/*/src/main/resources/COB-INF/sitemap.xmap`
- System sitemaps: `blocks/*/src/main/resources/COB-INF/system/sitemap.xmap`

### Block System

Blocks are modular components providing specific functionality. Each block typically contains:
- **-impl** module: Implementation code and configuration
- **-sample** module: Sample/demo code (if applicable)
- **pom.xml**: Maven module descriptor

Blocks can depend on other blocks and are deployed as separate JAR files with manifest entries identifying them as Cocoon blocks.

### Spring Container

Cocoon uses Spring Framework for:
- Component lifecycle management
- Dependency injection
- Configuration via `cocoon-spring-configurator`

Key modules:
- `core/cocoon-container` - Container abstraction
- `core/cocoon-configuration-api` - Configuration API
- `core/cocoon-spring-configurator` - Spring-based configuration

### Expression Language

Cocoon has its own expression language for use in sitemaps and configuration:
- `core/cocoon-expression-language` - Expression language implementation

## Testing

Run tests using Maven:

```bash
# Run all tests
./build.sh test

# Skip tests during build
./build.sh notest install

# Run tests for a specific module
cd core/cocoon-core
mvn test

# Run a single test class
cd core/cocoon-core
mvn test -Dtest=MyTestClass

# Run a single test method
cd core/cocoon-core
mvn test -Dtest=MyTestClass#myTestMethod
```

The `blocks/cocoon-it` module contains integration tests for the framework.

## Development Workflow

1. **IDE Setup**:
   - IntelliJ: Open the root directory directly
   - Eclipse: Run `./build.sh eclipse:clean eclipse:eclipse`, then import projects and set M2_REPO classpath variable

2. **Making Changes**:
   - Core framework changes go in `core/` modules
   - Block-specific changes go in `blocks/cocoon-{blockname}/`
   - Build tools changes go in `tools/`

3. **Adding a New Block**:
   - Create module in `blocks/` directory
   - Add to `blocks/pom.xml` modules or allblocks profile
   - Follow the structure of existing blocks

4. **Memory Issues**:
   - The build requires at least 512MB heap (set in `build.sh`)
   - Adjust `MAVEN_OPTS` if needed: `export MAVEN_OPTS="-Xmx1024m"`

## Key Java Packages

- `org.apache.cocoon.pipeline` - Pipeline API
- `org.apache.cocoon.sitemap` - Sitemap processing
- `org.apache.cocoon.generation` - Generator components
- `org.apache.cocoon.transformation` - Transformer components
- `org.apache.cocoon.serialization` - Serializer components
- `org.apache.cocoon.components` - Reusable components
- `org.apache.cocoon.servlet` - Servlet integration

## Maven Profiles

- **allblocks** - Builds all optional blocks (automatically activated by `build.sh`)
- **alldists** - Builds all distribution assemblies
- **eventcache-jms** - Builds eventcache and JMS blocks only
- **deprecated-axis** - Builds deprecated Axis block (use `-DincludeAxis`)
- **apache-release** - Apache release configuration

## Jakarta EE 9+ Migration Status

Apache Cocoon 2.3.1 is fully migrated to Jakarta EE 9+ for JDK 21 compatibility:

**Completed Migrations:**
- ✅ Servlet API: jakarta.servlet 5.0.0 (234 files migrated)
- ✅ Spring Framework: 6.1.14 (Jakarta EE 9+ ready)
- ✅ Jetty: 11.0.20 (Jakarta EE 9)
- ✅ JMS API: jakarta.jms-api 3.0.0

**Legacy Blocks (javax namespace - no Jakarta version available):**
- ⚠️ **Portal Block**: Uses javax.portlet 3.0 (Jakarta Portlet not yet released)
- ⚠️ **JCR Block**: Uses javax.jcr 2.0 (JCR is not part of Jakarta EE)

**Deprecated Blocks:**
- ❌ **Axis Block**: Uses obsolete Apache Axis 1.x (excluded from default builds)
  - Build with: `./build.sh install -DincludeAxis`

**Runtime Requirements:**
- JDK 21 required
- Compatible with: Tomcat 10+, GlassFish 7+, WildFly 27+ (Jakarta EE 9+ servers)
- Not compatible with: Tomcat 9.x, JBoss EAP 7.x (javax.servlet servers)

**Note:** Java SE APIs remain as javax.* (javax.xml.parsers, javax.imageio, javax.naming) - these are NOT Jakarta EE and do not need migration.

## Common Issues

- If the build fails with transient download errors, simply retry the build command
- Missing `cocoon-rcl` artifact: build the `tools/` directory first
- Out of memory errors: increase heap size in `build.sh` (MAVEN_OPTS)
- Eclipse setup: Ensure M2_REPO variable points to your local Maven repository (~/.m2/repository)
- **`cocoon.sh` fails to start / Spring context error on `SessionManager`**: The `cocoon-session-fw-impl` is pulled in transitively via `cocoon-authentication-fw-impl`. Ensure `Deprecation.java` in `core/cocoon-util` declares the `*_VALUE` constants **before** the `logger` field — the `LoggerWrapper` instance field `forbiddenLevel` is initialized at construction time from `ERROR_VALUE`, so if `logger` is declared before the constants, `forbiddenLevel` gets `0` instead of `3`, causing all deprecation `warn()` calls to throw `DeprecationException`.
- **`cocoon.sh` serves stale content**: The script uses the pre-built `target/cocoon-webapp` directory. If you change block dependencies, re-run `./build.sh install` (with `-P allblocks`) before `./cocoon.sh`.
