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

# Jakarta EE 9+ Migration Guide for Apache Cocoon Users

## Overview

Cocoon 2.3.1 has been migrated to Jakarta EE 9+ for compatibility with JDK 21 and modern application servers.

## Breaking Changes

### 1. Application Server Requirements

Cocoon 2.3.1 requires Jakarta EE 9+ compliant servers:

**Supported:**
- ✅ Apache Tomcat 10.x or later
- ✅ Eclipse GlassFish 7.x or later
- ✅ WildFly 27.x or later
- ✅ Jetty 11.x or later

**Not Supported:**
- ❌ Tomcat 9.x (uses javax.servlet)
- ❌ JBoss EAP 7.x (uses javax.servlet)
- ❌ Jetty 9.x/10.x (uses javax.servlet)

### 2. Custom Cocoon Components

If you have custom Cocoon components that use Servlet API:

**Change package imports:**
```java
// Old (Cocoon 2.3.0)
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// New (Cocoon 2.3.1)
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
```

### 3. Custom JMS Components

If you use the JMS block with custom code:

**Change package imports:**
```java
// Old
import javax.jms.Connection;
import javax.jms.MessageListener;

// New
import jakarta.jms.Connection;
import jakarta.jms.MessageListener;
```

## Non-Breaking Changes

### Portal Block (javax.portlet)

The Portal block still uses **javax.portlet** because Jakarta Portlet specification has not been released yet.

- ✅ No changes required to your portlet applications
- ✅ This is expected and correct behavior
- ⏳ Will migrate when Jakarta Portlet 4.0 is available

### JCR Block (javax.jcr)

The JCR block still uses **javax.jcr** because JCR is not part of Jakarta EE (it's a separate JSR).

- ✅ No changes required to your JCR applications
- ✅ javax.jcr is the correct namespace (not migrating)

### Axis Block (Deprecated)

The Axis block has been deprecated and excluded from default builds due to:
- Uses obsolete Apache Axis 1.x (released 2006)
- Known security vulnerabilities
- No Jakarta EE migration path for javax.xml.rpc

**Migration options:**
1. Migrate to Jakarta XML Web Services (recommended)
2. Migrate to REST APIs (recommended)
3. Continue using Axis (build with `-DincludeAxis`, not recommended)

## Upgrade Steps

### Step 1: Verify JDK 21

```bash
java -version
# Should show Java 21
```

### Step 2: Update Cocoon Dependencies

Update your project's `pom.xml`:

```xml
<dependency>
  <groupId>org.apache.cocoon</groupId>
  <artifactId>cocoon</artifactId>
  <version>2.3.1</version>
</dependency>
```

### Step 3: Update Package Imports (if needed)

Use your IDE's refactoring tools:
- Find: `import javax.servlet.`
- Replace: `import jakarta.servlet.`

### Step 4: Test Deployment

Deploy to a Jakarta EE 9+ server and test thoroughly.

## Common Issues

### Issue: ClassNotFoundException for javax.servlet classes

**Cause:** Running on Jakarta EE 9+ server but code still imports javax.servlet

**Solution:** Update all imports from javax.servlet to jakarta.servlet

### Issue: Portal block not building

**Cause:** Portal block uses javax.portlet (expected)

**Solution:** This is normal - portal block remains on javax.portlet until Jakarta Portlet is released

### Issue: Axis block missing

**Cause:** Axis is deprecated and excluded by default

**Solution:**
- Recommended: Migrate to Jakarta XML Web Services or REST
- Temporary: Build with `./build.sh install -DincludeAxis`

## Support

For migration assistance, consult:
- [Jakarta EE 9 Specification](https://jakarta.ee/specifications/platform/9/)
- [Apache Cocoon Documentation](http://cocoon.apache.org/)
- [Cocoon Mailing Lists](http://cocoon.apache.org/2.2/support.html)
