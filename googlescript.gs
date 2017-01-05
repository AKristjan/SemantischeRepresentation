
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


function doGet() {
  return HtmlService.createHtmlOutputFromFile('Index');
}

function getUnreadEmails() {
  return GmailApp.getInboxUnreadCount();
}


function getFileName() { 
  var fileName = DocumentApp.getActiveDocument().getName();
  PropertiesService.getScriptProperties().setProperty('fileName', fileName);
  return fileName;
}


function getViewer() {
  var viewerString = DocumentApp.getActiveDocument().getViewers().toString();
  PropertiesService.getScriptProperties().setProperty('viewer', viewerString);
  return viewerString;
}

function getEditors() {
  var editorString = DocumentApp.getActiveDocument().getEditors().toString();
  PropertiesService.getScriptProperties().setProperty('editor', editorString);
  return editorString;
}

function getURL() {
  var documentPath = DocumentApp.getActiveDocument().getUrl();    
  PropertiesService.getScriptProperties().setProperty('documentPath', documentPath);
  return documentPath;
}

function getID() {
  var driveDocumentID = DocumentApp.getActiveDocument().getId();
  PropertiesService.getScriptProperties().setProperty('driveDocumentID', driveDocumentID);
  return driveDocumentID;
}

