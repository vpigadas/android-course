<?php
$apiKey = "g8iuH@NBUWHDJkhnsie2w";
if (!(isset($_REQUEST['type']) && isset($_REQUEST['apikey']) && $_REQUEST['apikey'] == $apiKey)) {
	exit;
}
ini_set('memory_limit', '256M');
ini_set("max_execution_time", 120);
require_once("DBconnection.inc.php");
require_once("functions.inc.php");
switch ($_REQUEST['type']) {
	case 'get_channels':
		echo json_encode(array('channels' => getChannels($db, false)));
		break;
	case 'get_programme':
		echo json_encode(array('programme' => getChannelProgram($db, $_REQUEST['channel'])));
		break;
}
$db->closeDbConnection();
