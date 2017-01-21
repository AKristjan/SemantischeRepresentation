//View wird angelegt durch HTML Service und dem User Interface übergeben

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


// Getter für statische Metadaten
 
function getOwner() {
 
var fileNameID = DocumentApp.getActiveDocument().getId();
  
  try
{
  
   var ownerMail = DriveApp.getFolderById(fileNameID).getOwner().getEmail();
   var ownerName = DriveApp.getFolderById(fileNameID).getOwner().getName();
   var ownerdata = ownerName + ' , <br />' + ownerMail;

}
catch(err)
{
  
 Logger.log(err)

}
   return ownerdata;
}


function getURL() {
  var documentPath = DocumentApp.getActiveDocument().getUrl();    
  PropertiesService.getScriptProperties().setProperty('documentPath', documentPath);
  return documentPath;
}


function getFileName() { 
  var fileName = DocumentApp.getActiveDocument().getName();
  PropertiesService.getScriptProperties().setProperty('fileName', fileName);
  return fileName;
}


function getID() {
  var driveDocumentID = DocumentApp.getActiveDocument().getId();
  PropertiesService.getScriptProperties().setProperty('driveDocumentID', driveDocumentID);
  return driveDocumentID;
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


function getDateCreated() {
 var files = DriveApp.getFilesByName(PropertiesService.getScriptProperties().getProperty('fileName'));
 while (files.hasNext()) {
   var file = files.next();
   var creationDate = 'test'; 
   return " " + creationDate;
 }
}


function getLastUpdated() {
 var files = DriveApp.getFilesByName(PropertiesService.getScriptProperties().getProperty('fileName'));
 while (files.hasNext()) {
   var file = files.next();
   return ""+file.getLastUpdated();
 }
  
  var folders = DriveApp.getFolders();
 while (folders.hasNext()) {
   var folder = folders.next();
 }
}

 
//Dokumentproperties abfragen und setzen

function processMetadataForm(theForm) {
  var props=PropertiesService.getDocumentProperties()
  
  for (var item in theForm) {
    props.setProperty(item,theForm[item])
    Logger.log(item+':::'+theForm[item]);
  }
}


//Diese funktion wird beim Start des Addons aufgerufen
//Das Menü wird initalisiiert, eine View wird hinterlegt, abschließend zur UI hinzugefügt

function onOpen(e) {
  DocumentApp.getUi().createMenu('timing').addItem('Metadata Attributierung', 'metadataView').addToUi();
}



