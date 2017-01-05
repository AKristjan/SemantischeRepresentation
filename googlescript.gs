function onOpen(e) {
  DocumentApp.getUi().createAddonMenu()
      .addItem('Start', 'showSidebar')
      .addToUi();
}
 
function onInstall(e) {
  onOpen(e);
}

 
function showSidebar() {
  var ui = HtmlService.createHtmlOutputFromFile('Sidebar')
      .setTitle('Attributierung von Dokumenten');
  DocumentApp.getUi().showSidebar(ui);
//  showStaticAttributes(DocumentApp.getActiveDocument().getId());
  
}
 


function getFileName() { 
  var fileName = DocumentApp.getActiveDocument().getName();
  PropertiesService.getScriptProperties().setProperty('fileName', fileName);
  return fileName;
}


function getDateCreated() {
 var files = DriveApp.getFilesByName(PropertiesService.getScriptProperties().getProperty('fileName'));
 while (files.hasNext()) {
   var file = files.next();
   var creationDate = file.getDateCreated();
   PropertiesService.getScriptProperties().setProperty('creationDate', creationDate);
   return ""+creationDate;
 }
}

function getLastUpdated() {
 var files = DriveApp.getFilesByName(PropertiesService.getScriptProperties().getProperty('fileName'));
 while (files.hasNext()) {
   var file = files.next();
   return ""+file.getLastUpdated();
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



