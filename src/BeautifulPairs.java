/*
CPP code

@TODO write this myself
struct pt
{
    int x, y, id;
};

struct cmp_x
{
    bool operator()(const pt & a, const pt & b) const {
        return a.x < b.x || (a.x == b.x && a.y < b.y);
    }
};

struct cmp_y
{
    bool operator()(const pt & a, const pt & b) const {
        return a.y < b.y;
    }
};

class Solution {
public:

    int mindist;
    vector<pt> a;
    pair<int, int> best_pair;

    void upd_ans(const pt & a, const pt & b)
    {
        int dist = abs(a.x - b.x) + abs(a.y - b.y);

        pair<int,int> tmp={min(a.id, b.id),max(a.id, b.id)};

        if (dist < mindist)
        {
            mindist = dist;
            best_pair = tmp;
        }
        else if(dist==mindist)
        {
            if(best_pair.first>tmp.first)
            {
                best_pair = tmp;
            }
            else if(best_pair.first==tmp.first && best_pair.second>tmp.second)
            {
                best_pair = tmp;
            }
        }
    }

    vector<pt> t;

    void rec(int l, int r)
    {
        if (r - l <= 3)
        {
            for (int i = l; i < r; ++i)
            {
                for (int j = i + 1; j < r; ++j)
                {
                    upd_ans(a[i], a[j]);
                }
            }
            sort(a.begin() + l, a.begin() + r, cmp_y());
            return;
        }

        int m = (l + r) >> 1;
        int midx = a[m].x;
        rec(l, m);
        rec(m, r);

        merge(a.begin() + l, a.begin() + m, a.begin() + m, a.begin() + r, t.begin(), cmp_y());
        copy(t.begin(), t.begin() + r - l, a.begin() + l);

        int tsz = 0;
        for (int i = l; i < r; ++i)
        {
            if (abs(a[i].x - midx) <= mindist)
            {
                for (int j = tsz - 1; j >= 0 && a[i].y - t[j].y <= mindist; --j)
                    upd_ans(a[i], t[j]);
                t[tsz++] = a[i];
            }
        }
    }


    vector<int> beautifulPair(vector<int>& nums1, vector<int>& nums2) {

        t.resize(nums1.size());

        for(int i=0;i<nums1.size();i++)
        {
            a.push_back({nums1[i],nums2[i],i});
        }

        sort(a.begin(), a.end(), cmp_x());
        mindist = INT_MAX;

        rec(0, nums1.size());

        pair<int,int> ans={-1,-1};

        for(int i=0;i<a.size();i++)
        {
            for(int j=i+1;j<a.size();j++)
            {
                int dis=abs(a[i].x-a[j].x)+abs(a[i].y-a[j].y);

                if(dis==mindist)
                {
                    pair<int,int> tmp={min(a[i].id,a[j].id)+1,max(a[i].id,a[j].id)+1};

                    if(ans.first==-1)
                    {
                        ans=tmp;
                    }
                    else if(ans.first>tmp.first)
                    {
                        ans=tmp;
                    }
                    else if(ans.first==tmp.first && ans.second>tmp.second)
                    {
                        ans=tmp;
                    }
                }

                if(abs(a[i].x-a[j].x)>mindist)
                {
                    break;
                }
            }
        }

        return {min(best_pair.first,best_pair.second)+1,max(best_pair.first,best_pair.second)+1};
    }
};
 */