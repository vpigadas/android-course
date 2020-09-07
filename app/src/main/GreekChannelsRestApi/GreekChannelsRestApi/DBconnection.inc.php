<?php

class MySqlDatabase {

    private $con = null;
    private $host = 'localhost';
    private $database = 'dimitris619033_channels_db';
    private $username = 'greekchannels_usr';
    private $password = 'huHIHR*#&uwhw2';

    public function __construct() {
        $this->createConnection();
    }

    public function createConnection() {
        $this->con = new mysqli($this->host, $this->username, $this->password, $this->database);
        if (mysqli_connect_errno()) {
            printf("Connection to database server failed: %s\n", mysqli_connect_error());
            exit();
        }
        /* change character set to utf8 */
        if (!$this->con->set_charset("utf8")) {
            printf("Error loading character set utf8: %s\n", $this->con->error);
        }
    }

    function specialDBCommands($command, $table) {
        switch (strtolower($command)) {
            case 'truncate':
                $query = 'TRUNCATE TABLE ' . $table;
                break;
            case 'drop':
                $query = 'DROP TABLE ' . $table;
                break;
        }
        $this->con->query($query);
    }

    function fetchQueryObject($query, $specificField = false, $toInt = false) {
        if ($result = $this->con->query($query)) {
            $obj = $result->fetch_object();
            $result->close();
            if ($obj != null) {
                return (($specificField != false) ? (($toInt) ? (int) $obj->$specificField : $obj->$specificField) : $obj);
            }
        }
        return null;
    }

    function fetchQueryArray($query, $specificField = false, $toInt = false) {
        $resultArray = array();
        if ($result = $this->con->query($query)) {
            while ($obj = $result->fetch_object()) {
                $resultArray[] = ($specificField != false) ? (($toInt) ? (int) $obj->$specificField : $obj->$specificField) : $obj;
            }
            $result->close();
        }
        return $resultArray;
    }

    function prepareStmt($query) {
        $stmt = $this->con->prepare($query);
        if(!$stmt) {
            logErrors('Prepare failed: (' . $this->con->errno . ') ' . $this->con->error);
        }
        return $stmt;
    }

    function executePreparedStmt($stmt, $arrParams, $insertStmt = true, $id = 0, $logErrors = true) {
        $result = false;
        call_user_func_array(array($stmt, 'bind_param'), $arrParams);
        if (!$stmt->execute()) {
            if ($logErrors) {
                $tracedFunctions = array();
                foreach (debug_backtrace() as $trace) {
                    $tracedFunctions[] = $trace['function'];
                }
                logErrors('Function Trace: ' . implode(' | ', $tracedFunctions) . "\nParameters: " . print_r($arrParams, true) . $stmt->error);
            }
        } else if ($insertStmt) {
            $result = $stmt->insert_id;
        } else if ($id > 0) {
            $result = $id;
        } else {
            $result = $stmt->affected_rows;
        }
        return $result;
    }

    function closeStmt($stmt) {
        $stmt->close();
    }

    function executeSinglePreparedStmt($query, $arrParams, $insertStmt = true, $id = 0, $logErrors = true) {
        $result = false;
        if ($stmt = $this->prepareStmt($query)) {
            $result = $this->executePreparedStmt($stmt, $arrParams, $insertStmt, $id, $logErrors);
            $this->closeStmt($stmt);
        }
        return $result;
    }

    function ifNull() {
        return 'IFNULL';
    }

    function escapeObjectName($name) {
        return '`' . $name . '`';
    }

    function escapeDbField($input) {
        return $this->con->real_escape_string($input);
    }

    function extractYear($field) {
        return "YEAR(" . $field . ")";
    }

    function extractMonth($field) {
        return "MONTH(" . $field . ")";
    }

    function extractDay($field) {
        return "DAY(" . $field . ")";
    }

    function extractHour($field) {
        return "HOUR(" . $field . ")";
    }

    function extractMinute($field) {
        return "MINUTE(" . $field . ")";
    }

    function extractSecond($field) {
        return "SECOND(" . $field . ")";
    }

    function extractDayOfYear($field) {
        return "DAYOFYEAR(" . $field . ")";
    }

    function extractWeek($field) {
        return "WEEK(" . $field . ")";
    }

    function extractYearWeek($field) {
        return "YEARWEEK(" . $field . ")";
    }

    function toChar($field) {
        return $field;
    }

    function toDate($date, $withQuotes = true) {
        if ($withQuotes) {
            $date = "'" . $date . "'";
        }
        return $date;
    }

    function parseDate($field) {
        return 'DATE(' . $field . ')';
    }

    function parseTime($field) {
        return "DATE_FORMAT(" . $field . ", '%H:%i')";
    }

    function dateFormat($field, $format) {
        return "DATE_FORMAT(" . $field . ", '" . $format . "')";
    }

    function stringToDateFunc() {
        return "STR_TO_DATE";
    }

    function stringToDate($string, $format) {
        return "STR_TO_DATE(" . $string . ", '" . $format . "')";
    }

    function dateDiff() {
        return 'DATEDIFF';
    }

    function timeFromNowDiff($field) {
        return 'timediff(NOW(), ' . $field . ')';
    }

    function yearAlias() {
        return '%Y';
    }

    function yearSmallAlias() {
        return '%y';
    }

    function monthAlias() {
        return '%m';
    }

    function dayAlias() {
        return '%d';
    }

    function greekFullDateFormat() {
        return '%d-%m-%Y %H:%i:%s';
    }

    function fullDateFormat() {
        return '%Y-%m-%d %H:%i:%s';
    }

    function yearToHourFormat() {
        return '%Y-%m-%d %H';
    }

    function yearToMinuteFormat() {
        return '%d%m%Y %H:%i';
    }

    function yearToDayFormat() {
        return '%Y-%m-%d';
    }

    function minToSecFormat() {
        return '%H%i%s';
    }

    function closeDbConnection() {
        $this->con->close();
    }

}
$db = new MySqlDatabase();