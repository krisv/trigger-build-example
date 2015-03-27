package demo.build;

import java.util.HashMap;
import java.util.Map;

import org.kie.api.runtime.process.WorkItem;
import org.kie.api.runtime.process.WorkItemHandler;
import org.kie.api.runtime.process.WorkItemManager;

public class BuildWorkItemHandler implements WorkItemHandler {

	public void executeWorkItem(WorkItem workItem, WorkItemManager manager) {
		String buildId = (String) workItem.getParameter("BuildId");
		System.out.println("Executing " + buildId + " (workItemId = " + workItem.getId() + ")");
		Map<String, Object> results = new HashMap<String, Object>();
		if (buildId != null && buildId.startsWith("Auto")) {
			results.put("Outcome", "Success");
			results.put("ResultUrl", "http://www.jbpm.org");
			manager.completeWorkItem(workItem.getId(), results);
		}
		// else you could trigger completion using:
		// POST http://localhost:8080/jbpm-console/rest/runtime/demo:Build:1.0/workitem/INSERT_WORKITEMID_HERE/complete?map_Outcome=Success&map_ResultUrl=www.jbpm.org
	}

	public void abortWorkItem(WorkItem workItem, WorkItemManager manager) {
		System.out.println("Aborting " + workItem.getParameter("BuildId"));
	}

}
