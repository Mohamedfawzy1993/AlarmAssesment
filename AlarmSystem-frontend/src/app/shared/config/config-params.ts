export class ConfigParams {


  // Server Locations
  static SERVER_URL = 'http://localhost:8080/AlarmSys/resources/';
  static WEBSOCKET_URL = 'ws://localhost:8080/';

  // Routes
  static DASHBOARD_ROUTE = 'dashboard';
  static HOME_ROUTE = '';
  static GENERATOR_ROUTE = 'generator';
  static ALARMS_ROUTE = 'alarms';

  // Services URL
  static ALARM_URL = ConfigParams.SERVER_URL + 'alarm/';
  static GENERATOR_URL = ConfigParams.SERVER_URL + 'scheduler/alarm/';
  static STATISTICS_URL = ConfigParams.SERVER_URL + 'statistics/';
  static COMMENT_URL = ConfigParams.SERVER_URL + 'comment/';


  // Service Config Params
  static PAGINATION_PAGE_SIZE_DEFAULT_VALUE = 50;

  // Severities

  static WARNING = 'warning';
  static MINOR = 'minor';
  static MAJOR = 'major';
  static CRITICAL = 'critical';
}
