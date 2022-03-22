package org.joget.marketplace;

import java.util.HashMap;
import java.util.Map;
import org.joget.apps.app.model.AppDefinition;
import org.joget.apps.app.service.AppPluginUtil;
import org.joget.apps.app.service.AppUtil;
import org.joget.apps.datalist.model.DataList;
import org.joget.apps.datalist.model.DataListPluginExtend;
import org.joget.plugin.base.PluginManager;
import org.joget.workflow.util.WorkflowUtil;
import org.joget.plugin.enterprise.BeanShellDatalistAction;

public class BeanshellDatalistActionWithHTML extends BeanShellDatalistAction implements DataListPluginExtend{
    private final static String MESSAGE_PATH = "messages/beanshellDatalistActionWithHTML";
 
    public String getName() {
        //support i18n
        return AppPluginUtil.getMessage("org.joget.marketplace.BeanshellDatalistActionWithHTML.name", getClassName(), MESSAGE_PATH);
    }

    public String getVersion() {
        return "7.0.0";
    }

    public String getClassName() {
        return getClass().getName();
    }
    
    public String getLabel() {
        //support i18n
        return AppPluginUtil.getMessage("org.joget.marketplace.BeanshellDatalistActionWithHTML.name", getClassName(), MESSAGE_PATH);
    }
    
    public String getDescription() {
        //support i18n
        return AppPluginUtil.getMessage("org.joget.marketplace.BeanshellDatalistActionWithHTML.desc", getClassName(), MESSAGE_PATH);
    }

    public String getPropertyOptions() {
        AppDefinition appDef = AppUtil.getCurrentAppDefinition();
        String appId = appDef.getId();
        String appVersion = appDef.getVersion().toString();
        Object[] arguments = new Object[]{appId, appVersion, appId, appVersion};
        String json = AppUtil.readPluginResource(getClass().getName(), "/properties/beanshellDatalistActionWithHTML.json", arguments, true, MESSAGE_PATH);
        
        return json;
    }

    @Override
    public String getHTML(DataList dataList) {
        PluginManager pluginManager = (PluginManager) AppUtil.getApplicationContext().getBean("pluginManager");
        Map dataModel = new HashMap();
        dataModel.put("datalist", dataList);
        dataModel.put("element", this);
        dataModel.put("htmlScript", getPropertyString("htmlScript"));
        dataModel.put("contextPath", WorkflowUtil.getHttpServletRequest().getContextPath());
        
        return pluginManager.getPluginFreeMarkerTemplate(dataModel, "org.joget.marketplace.BeanshellDatalistActionWithHTML", "/templates/beanshellDatalistActionWithHTML.ftl", null);
       
    }
    
}
