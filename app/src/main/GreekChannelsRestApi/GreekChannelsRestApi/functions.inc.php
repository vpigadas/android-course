<?php

function getTableField($db, $tableName, $fieldName, $id) {
    $query = "SELECT $fieldName " .
            "FROM $tableName " .
            "WHERE id=" . filter_var($id, FILTER_SANITIZE_NUMBER_INT);
    return $db->fetchQueryObject($query)->$fieldName;
}

function getDistinctTableField($db, $tableName, $fieldName) {
    $query = "SELECT DISTINCT $fieldName " .
            "FROM $tableName " .
            "ORDER BY $fieldName";
    return $db->fetchQueryArray($query, $fieldName);
}

function getChannel($db, $id) {
    $query = "SELECT id, title, description, icon, website, enabled " .
            "FROM channels " .
            "WHERE id=" . filter_var($id, FILTER_SANITIZE_NUMBER_INT);
    return $db->fetchQueryObject($query);
}

function getChannels($db, $menuOnly = true, $enabled = 1, $specificField = false) {
    $enabledFilter = ($enabled != null) ? ' WHERE enabled = ' . $db->escapeDbField($enabled) : '';
    $query = "SELECT id, title, icon, website " . (($menuOnly) ? "" : ", description, enabled ")
            . "FROM channels" . $enabledFilter . " ORDER BY title";
    return $db->fetchQueryArray($query, $specificField);
}


function getChannelProgram($db, $channelId) {
    $query = "SELECT id, title, description, start_time, end_time, TIME(start_time) as start, TIME(end_time) as end, channel_id "
            . "FROM channel_programme " .
			"WHERE channel_id=" . filter_var($channelId, FILTER_SANITIZE_NUMBER_INT) .
		" ORDER BY start_time ";
    return $db->fetchQueryArray($query);
}