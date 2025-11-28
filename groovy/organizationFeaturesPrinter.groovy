import de.hybris.platform.servicelayer.search.FlexibleSearchQuery
import com.hs.core.model.HSOrganizationModel

// Assume flexibleSearchService is injected and available

String orgQueryString = """
    SELECT {pk}
    FROM {HSOrganization}
    ORDER BY {creationtime} DESC
"""
FlexibleSearchQuery orgQuery = new FlexibleSearchQuery(orgQueryString)
orgQuery.setCount(1) // Only fetch the latest

def orgResult = flexibleSearchService.search(orgQuery)

if (orgResult.getCount() > 0) {
    HSOrganizationModel latestOrg = orgResult.getResult().get(0)
    Map orgFeatureMap = latestOrg.getFeatures() // key: EnumerationValueModel, value: Boolean

    if (orgFeatureMap && !orgFeatureMap.isEmpty()) {
        println "Features map for the latest HSOrganization (
    ${latestOrg.getUid()}):"
        String mapString = orgFeatureMap.collect { k, v -> "${k?.getCode()}|${v}" }.join('\n')
        println mapString
    } else {
        println "No features found for the latest HSOrganization (${latestOrg.getUid()})."
    }
} else {
    println "No HSOrganization found."
}