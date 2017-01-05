
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




   