function lastFriday(year, month) {
 const lastDay = new Date(year, month, 0);
 const daysBack = (lastDay.getDay() - 5 + 7) % 7;
 return lastDay.getDate() - daysBack;
}
