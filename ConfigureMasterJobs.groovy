job("Create Scheduled Job") {
    parameters {
        validatingStringParameterDefinition {
            name 'Title'
            regex '.+'
            defaultValue ''
            failedValidationMessage 'Must provide a job title'
            description 'Job title for easy reference'
        }
        validatingStringParameterDefinition {
            name 'Department'
            regex '.+'
            defaultValue ''
            failedValidationMessage 'Must provide a department subdomain'
            description 'Department subdomain'
        }
        validatingStringParameterDefinition {
            name 'CommandName'
            regex '.+'
            defaultValue ''
            failedValidationMessage 'Must provide a command name'
            description 'Command name'
        }
        validatingStringParameterDefinition {
            name 'Schedule'
            regex '.* .* .* .* .*'
            defaultValue ''
            failedValidationMessage 'Must provide a schedule'
            description 'CRON job schedule'
        }
        stringParam('DepartmentId', null, 'Department Id')
        stringParam('ConfiguredId', null, 'Configured Id')
        booleanParam('TransientJob', false, 'Is this a transient job')
        stringParam('Timeout', null, 'Job timeout')
        textParam('StatusChangeHandler', null, 'Specify a status change handler if applicable')
        textParam('ArgList', null, 'Additional Args')
    }

    steps {
        dsl {
            text(readFileFromWorkspace('ConfigureScheduledJob.groovy'))
        }
    }
}

job("Create Manual Job") {
    parameters {
        validatingStringParameterDefinition {
            name 'Title'
            regex '.+'
            defaultValue ''
            failedValidationMessage 'Must provide a job title'
            description 'Job title for easy reference'
        }
        validatingStringParameterDefinition {
            name 'Department'
            regex '.+'
            defaultValue ''
            failedValidationMessage 'Must provide a department subdomain'
            description 'Department subdomain'
        }
        validatingStringParameterDefinition {
            name 'CommandName'
            regex '.+'
            defaultValue ''
            failedValidationMessage 'Must provide a command name'
            description 'Command name'
        }
        stringParam('DepartmentId', null, 'Department Id')
        stringParam('ConfiguredId', null, 'Configured Id')
        booleanParam('TransientJob', false, 'Is this a transient job')
        stringParam('Timeout', null, 'Job timeout')
        textParam('StatusChangeHandler', null, 'Specify a status change handler if applicable')
        textParam('ArgList', null, 'Additional Args')
    }

    steps {
        dsl {
            text(readFileFromWorkspace('ConfigureManualJob.groovy'))
        }
    }
}

listView('Manual Jobs') {
    description('Jobs that need to be kicked off manually')
    jobs {
        regex(/.*(m|M)anual.*/)
    }
    columns {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()
    }
}
listView('Scheduled Jobs') {
    description('Jobs that run on a predefined schedule')
    jobs {
        regex(/.*(S|s)cheduled.*/)
    }
    columns {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()
    }
}
listView('Control Center') {
    description('')
    jobs {
        name('Create Manual Job')
        name('Create Scheduled Job')
    }
    columns {
        status()
        weather()
        name()
        lastSuccess()
        lastFailure()
        lastDuration()
        buildButton()
    }
}

