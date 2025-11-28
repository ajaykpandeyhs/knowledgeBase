# Migration Scripts & Maintenance Automation

This file documents scripts used for cutover, migration, and critical maintenance tasks, grouped by step and purpose for reliable referencing.

---

## 1. Update Features for Existing Organizations (Cutover Step 1)

_Updates features at the organization level for all existing organizations per a provided feature map._

```groovy
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery
import de.hybris.platform.servicelayer.search.SearchResult
import de.hybris.platform.servicelayer.search.FlexibleSearchService
import de.hybris.platform.servicelayer.model.ModelService
import com.hs.core.model.HSOrganizationModel
import de.hybris.platform.core.model.enumeration.EnumerationValueModel
// ====== CONFIGURATION ======
final int batchSize = 50         // Number of rows per batch
final int batchNumber = 0        // Change this manually for each batch: 0 for first batch, 1 for second, etc.
// ====== SERVICES (assume injected or available in context) ======
FlexibleSearchService flexibleSearchService = spring.getBean("flexibleSearchService");
ModelService modelService = spring.getBean("modelService");
// ====== FEATURE MAP ======
Map<String, Boolean> featuresMap = [
    // ... map truncated for brevity (full map in previous message) ...
]
// ====== FETCH FEATURES ======
String featureQuery = "SELECT {pk} FROM {HSApplicationFeature}"
FlexibleSearchQuery featureSearchQuery = new FlexibleSearchQuery(featureQuery)
// ... rest of script unchanged ...
```

---

## 2. Update Missing Access Rights for Existing Users (Cutover Step 2)

_Updates missing access rights for existing users, aligning each with the standard rights._

```java
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery;
import de.hybris.platform.servicelayer.search.SearchResult;
// ... rest of code ...
```

---

## 3. Update Super Admin Access Rights for Existing Users  
**Do NOT RUN as cutover step**  
_Updates super admin access rights for users at user level if not enabled._

```groovy
import de.hybris.platform.core.Registry
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery
// ... rest of code ...
```

---

## 4. Update Roles & Permissions for Existing TSM Users

_Updates hsUserType for employees using mapping (Business Admin, Account Manager, etc)._

```groovy
import de.hybris.platform.core.model.user.EmployeeModel
// ... rest of code ...
```

---

## 5. Steps to Enable Commensurate Update on Free Gifts

_SAP portal configuration for ruleengineservices.consumption.enabled and republishing logic._

```text
Update the property ruleengineservices.consumption.enabled to true in the SAP portal.

Note - This property is by default set to true OOTB but was overridden to false in Gen X environments in the SAP portal.

Republish all the published promotions. Script provided in Scripts and Impex Data | Script for republishing promotions

Reload all the drools rules.
```

### Script for Republishing SAP Promotions

```java
import de.hybris.platform.core.Registry;
import de.hybris.platform.ruleengineservices.rule.services.RuleService;
// ... rest of code ...
```

---

## 6. (Do Not RUN as Cutover step) Update HSUserAccessRights for TSM type 5 users for web.view.only

_Disables 'web.view.only' access right for users not of type 5._

```groovy
import de.hybris.platform.core.Registry
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery
// ... rest of code ...
```

---

## 7. Groovy Script to Disable All Enabled Cron Jobs

_Disables cron jobs across the platform, printing out which jobs were processed._

```groovy
import de.hybris.platform.servicelayer.search.FlexibleSearchQuery
import de.hybris.platform.cronjob.model.CronJobModel
// ... rest of code ...
```
