
import groovy.json.JsonSlurper

def jsonSlurper = new JsonSlurper()
def object = jsonSlurper.parseText(
        '{"count":48,"filters":{"areas":[2072],"permission":"TIER_1"},"teams":[{"name":"Arsenal FC"}]}'
)

object.teams[0].name