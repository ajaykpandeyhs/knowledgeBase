# Impex Data Files & Migration Steps

This file documents Impex references and import actions needed for TSM and migration tasks.

---

## Impex for TSM Usergroups

_Imports TSM-related user groups using the TSMUserGroups.impex file._

- **File:** TSMUserGroups.impex

---

## Impex for TSM Access Rights

_Imports TSM-related user access rights._

- **File:** TSMUserAccessRights.impex

---

## Impex for TSM User Template and Territory Mapping

_Imports mapping between user template and territory for TSM users._

- **File:** TSMTGToUserType.impex

---

## Impex for TSM User Template Access Right and Territory Mapping

_Imports access right and territory mapping for TSM user templates._

- **File:** HSUserTemplateAccessRight_GENX2.0.impex

---

## Steps/Queries for existing users

For existing users, use the following flexible search:

```sql
SELECT {userTemplateAccessRight.pk} FROM {HSUserTemplateAccessRight as userTemplateAccessRight JOIN HSAccessRight as accessRight on {userTemplateAccessRight.hsAccessRight}={accessRight.pk} JOIN HSUserTemplate as userTemplate on {userTemplateAccessRight.hsUserTemplate}={userTemplate.pk}  JOIN ENUMERATIONVALUE as enum on {enum.pk}={userTemplate.userType}} WHERE {enum.code}=?userType AND {userTemplateAccessRight.enabled}=1 AND {userTemplate.organizationId} is null
```