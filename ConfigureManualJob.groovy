job("$Title") {
    environmentVariables(Department: "$Department",
            CommandName: "$CommandName",
            DepartmentId: "$DepartmentId",
            ConfiguredId: "$ConfiguredId",
            TransientJob: "$TransientJob",
            Timeout: "$Timeout",
            StatusChangeHandler: "$StatusChangeHandler",
            ArgList: "$ArgList")
    steps {
        shell(readFileFromWorkspace('../Initialize/RunJob.sh'))
    }
    publishers {
        textFinder("\"success\":false", "", true, false, false)
    }
}