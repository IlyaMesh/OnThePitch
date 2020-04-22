/*
 * Copyright (c)
 */

package com.onthepitch.backend.repos.query;

public class QueryUtils {

    public static final String FIND_All_CLUBS = "select c from Club c order by c.club_name";

    public static final String
        FIND_POSTS_WITH_RATING = "select p.* from POST p" +
        "    left join (\n" +
        "        select rat.note_id, sum(case when rat.is_like = 1 then 1 else -1 end) as likes \n" +
        "        from RATING rat\n" +
        "        group by rat.note_id\n" +
        "    ) r on\n" +
        "    r.note_id = p.post_id\n" +
        "order by r.likes desc nulls last";

    public static final String PAGEABLE_COUNT_FOR_POSTS = "select count(*) from POST";

    public static final String FIND_ALL_COMMENTS_OF_POST =
        "select c from Comment c where c.post.post_id = :id order by c.replyTo.comment_id nulls last";

    public static final String FIND_ROOT_COMMENTS =
        "select c from Comment c where c.replyTo is null and c.post.post_id = :id";

    public static final String FIND_MATCHES_OF_FAV_TEAM =
        "select m from Match m where m.homeTeam.club_id = :favTeamId or m.awayTeam.club_id = :favTeamId order by m.matchTime";

    public static final String FIND_MATCHES_NEEDED_TO_UPDATE =
        "SELECT m FROM Match m where m.lastUpdated < m.matchTime and m.matchTime<:today";

    public static final String FIND_MATCH_THAT_GONE =
        "SELECT m from Match m where m.homeTeamScored is not null and m.awayTeamScored is not null and m.league = :league and m.season = :season";

    public static final String FIND_POST_BY_TEXT =
        "select p from Post p where p.header like %:text% or p.text like %:text%";

    public static final String FIND_RATING_BY_NOTE =
        "select r from Rating r where r.note_id =:id";

    public static final String FIND_RATING_BY_USER_AND_NOTE =
        "select r from Rating r where r.user =:user and r.note_id=:note_id";

    public static final String DELETE_RATING_BY_USER_AND_NOTE =
        "delete from Rating r where r.user=:user and r.note_id=:note_id";

    public static final String DELETE_RATING_BY_NOTE =
        "delete from Rating r where r.note_id=:note_id";

    public static final String UPDATE_USER =
        "update User u set u.user_pic = :link where u.username = :username";
}
