export class Util {

  public static dateStringToDateObject(dateString) {
    if (dateString == '' || dateString == null) {
      return null;
    }
    dateString = new Date(dateString);
    return dateString;
  }
}
