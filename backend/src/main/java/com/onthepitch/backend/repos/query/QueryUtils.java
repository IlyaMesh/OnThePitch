/*
 * Copyright (c)
 */

package com.onthepitch.backend.repos.query;

public class QueryUtils {

    public static final String FIND_All_CLUBS = "select c from Club c order by c.club_name";

    //use string because i need Const
    public static final String
        FIND_POSTS_WITH_RATING ="select p.* from POST p" +
                                "    left join (\n" +
                                "        select rat.note_id, count(rat.is_like) as likes \n" +
                                "        from RATING rat\n" +
                                "        where rat.is_like = 1\n" +
                                "        group by rat.note_id\n" +
                                "    ) r on\n" +
                                "    r.note_id = p.post_id\n" +
                                "order by r.likes";

    public static final String PAGEABLE_COUNT_FOR_POSTS = "select count(*) from POST";

    public static String getFIND_ALL_COMMENTS_OF_POST(String id) {
        return new StringBuilder("select c from Comment c where c.post.post_id = ").append(id).append(" order by c.replyTo.comment_id nulls last").toString();
    }

}
