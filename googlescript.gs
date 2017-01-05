
function metadataView() {
  html= HtmlService
      .createTemplateFromFile('metadata')
      .evaluate()
      .setSandboxMode(HtmlService.SandboxMode.IFRAME);
  
  DocumentApp.getUi().showSidebar(html);
}


function showSidebar() {
  var ui = HtmlService.createHtmlOutputFromFile('metadata')
      .setTitle('Attributierung von Dokumenten');
  DocumentApp.getUi().showSidebar(ui);
}


// Getter Statische Metadaten
function getFileName() { 
             
            return DocumentApp.getActiveDocument().getName();
   
}

function getURL() {
  return DocumentApp.getActiveDocument().getUrl();    
}

function getID() {
  return DocumentApp.getActiveDocument().getId();
}


