class Solution {
public:
    int busyStudent(vector<int>& startTime, vector<int>& endTime,
                    int queryTime) {
        int time_sz = startTime.size();
        int cnt = 0;
        for (int i = 0; i < time_sz; ++i) {
            if (startTime[i] <= queryTime && queryTime <= endTime[i]){
                cnt++;
            }
        }
        return cnt;
    }
};
