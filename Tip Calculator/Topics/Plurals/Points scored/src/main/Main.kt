fun getScore(pointsScored: Int, totalPoints: Int): String {
    // Write your code here!
    return resources.getString(R.string.students_score,
        "${resources.getQuantityString(R.plurals.points, pointsScored, pointsScored)}", totalPoints)
}