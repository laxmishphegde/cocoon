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

## Module Overview

`cocoon-portal-impl` is the implementation module of the Apache Cocoon Portal block. It provides a portal framework with aspect-based rendering, an event-driven interaction model, profile management, and Spring integration.

**Artifact:** `org.apache.cocoon:cocoon-portal-impl:2.3.1-SNAPSHOT`
**Parent:** `org.apache.cocoon:cocoon-portal`

## Build and Test Commands

```bash
# Build and install this module only (from this directory)
mvn install

# Run tests only
mvn test

# Run a single test class
mvn test -Dtest=DefaultEventManagerTestCase

# Build without tests
mvn install -DskipTests

# Build with the parent chain (resolves inter-module dependencies)
cd W:/tpty/cocoon && mvn -pl blocks/cocoon-portal/cocoon-portal-impl -am install
```

## Architecture

### Aspect Chain Pattern

The dominant pattern throughout this module. Chains are configured in Spring XML and executed sequentially, allowing pipeline-style extensibility without subclassing.

Three independent aspect chains exist:
- **RendererAspect chain** — processes layout rendering in `layout/renderer/aspect/impl/`
- **RequestProcessorAspect / ResponseProcessorAspect chains** — process portal requests/responses in `services/aspects/impl/`
- **ProfileManagerAspect chain** — hooks into profile load/store in `profile/impl/`

Each chain follows: `AspectChainImpl` → `AspectContextImpl` → individual `*Aspect` implementations.

### Event System (`event/`)

Publish/subscribe model managed by `DefaultEventManager`. Events are serialized to URI parameters via `DefaultEventConverter` (JXPath-based). Three event categories:
- **Coplet events** — coplet instance lifecycle and sizing
- **Layout events** — tab changes, parameter changes, add/remove
- **User events** — login, logout, access

`ReceiverBeanPostProcessor` auto-registers Spring beans that implement receiver interfaces.

### Rendering Pipeline (`layout/renderer/`)

`AspectRenderer` drives a chain of `RendererAspect` implementations. Predefined aspects:
- `CompositeContentAspect` / `TabContentAspect` — composite layout children
- `WindowAspect` — window chrome (title, minimize/maximize buttons)
- `DefaultCopletAspect` / `IncludeCopletAspect` / `CIncludeCopletAspect` — coplet content inclusion
- `XSLTAspect` — XSLT transformation
- `FrameAspect`, `RemovableAspect`, `ParameterAspect` — frame, remove button, parameters

### Profile Management (`profile/`)

Profiles represent user portal layout state. Two implementations:
- `StaticProfileManager` — fixed profiles from config
- `GroupBasedProfileManager` — profiles per user group with merge logic

`ProfileStore` is the storage abstraction; `ProfileHolder` holds in-memory state.

### Spring Integration (`spring/`)

Custom Spring XML namespace (`http://cocoon.apache.org/schema/portal`) handled by `PortalNamespaceHandler`. Schema at `org/apache/cocoon/portal/spring/schema/cocoon-portal-1.0.xsd`. Provides XML elements:
- `renderer-aspects`, `request-processor-aspects`, `response-processor-aspects`, `profile-manager-aspects` — define aspect chains
- `layout-type`, `coplet-type` — register portal object types

`RegistrationBeanPostProcessor` populates the four bean maps (`RendererMap`, `CopletAdapterMap`, `CopletTypeMap`, `LayoutTypeMap`) used for dynamic dispatch.

### Key Base Classes

- `AbstractBean` (`util/AbstractBean.java`) — inject `PortalService` and logger into any portal bean; most implementation classes extend this
- `AbstractPortalService` — base for service-layer beans
- `AbstractCopletAdapter` — base for coplet adapters with thread-pool support

## Key Configuration

**Spring beans:** `src/main/resources/org/apache/cocoon/portal/cocoon-portal-components.xml`

Defines all default service beans. Aspect chains (`requestAspects`, `responseAspects`, `ProfileManager.aspects`) are declared empty here and are meant to be overridden/extended by portal application configs.

**Spring namespace registration:** `src/main/resources/META-INF/spring.handlers` and `spring.schemas`

## Jakarta EE 9+ Note

This module uses `jakarta.servlet` (5.0), but retains `javax.portlet` 3.0 — there is no Jakarta Portlet release yet. Do not migrate `javax.portlet` imports.
