package ademar.study.reddit.core.test

import ademar.study.reddit.core.model.Error
import ademar.study.reddit.core.model.Post
import ademar.study.reddit.core.model.internal.*
import org.mockito.Mockito.`when` as whenever

object Fixture {

    object child {

        val JSON = """
        {
            "kind": "t3",
            "data": ${post.JSON}
        }
        """

        fun makeModel(): Child {
            val model = Child()
            model.post = post.makeModel()
            return model
        }

    }

    object comment {

        val AUTHOR = "pranavsharma0096"
        val TEXT = "Faster than Google's own devices. I'm impressed"
        val DOWNS = 2L
        val UPS = 7L
        val JSON = """
        {
          "subreddit_id": "t5_2qlqh",
          "banned_by": null,
          "removal_reason": null,
          "link_id": "t3_5s1dkg",
          "likes": null,
          "replies": "",
          "user_reports": [],
          "saved": false,
          "id": "ddbppd1",
          "gilded": 0,
          "archived": false,
          "report_reasons": null,
          "author": "$AUTHOR",
          "parent_id": "t3_5s1dkg",
          "score": 1,
          "approved_by": null,
          "controversiality": 0,
          "body": "$TEXT",
          "edited": false,
          "author_flair_css_class": null,
          "downs": $DOWNS,
          "body_html": "&lt;div class=\"md\"&gt;&lt;p&gt;Faster than Google&amp;#39;s own devices. I&amp;#39;m impressed&lt;/p&gt;\n&lt;/div&gt;",
          "stickied": false,
          "subreddit": "Android",
          "score_hidden": true,
          "name": "t1_ddbppd1",
          "created": 1486254786,
          "author_flair_text": null,
          "created_utc": 1486225986,
          "ups": $UPS,
          "mod_reports": [],
          "num_reports": null,
          "distinguished": null
        }
        """

        fun makeModel(): Comment {
            val model = Comment()
            model.replies = null
            model.author = AUTHOR
            model.text = TEXT
            model.downs = DOWNS
            model.ups = UPS
            return model
        }

    }

    object data {

        val JSON = """
        {
            "modhash": "",
            "children": [${child.JSON}]
        }
        """

        fun makeModel(): Data {
            val model = Data()
            model.children = listOf(child.makeModel())
            return model
        }

    }

    object error {

        val CODE = 1
        val MESSAGE = "Some error"
        val JSON = """
        {
            "code": $CODE,
            "message": "$MESSAGE"
        }
        """

        fun makeException(): Throwable {
            return Exception("Some error")
        }

        fun makeModel(): Error {
            val model = Error()
            model.code = CODE
            model.message = MESSAGE
            return model
        }

    }

    object post {

        val TITLE = "Galaxy S8 press render leak"
        val AUTHOR = "Idb996"
        val THUMBNAIL = "http://b.thumbs.redditmedia.com/4pTVmV1JFSdgaGFpXwSufG1RQFOQYy2FNoimEndxtJg.jpg"
        val CREATED = 1485666124L
        val COMMENTS = 7L
        val DOWNS = 1L
        val UPS = 3L
        val REFERENCE = "t3_5qpvlr"
        val LINK = "/r/Android/comments/5qpvlr/galaxy_s8_press_render_leak/"
        val JSON = """
        {
          "contest_mode": false,
          "banned_by": null,
          "domain": "twitter.com",
          "subreddit": "Android",
          "selftext_html": null,
          "selftext": "",
          "likes": null,
          "suggested_sort": null,
          "user_reports": [],
          "secure_media": null,
          "saved": false,
          "id": "5qpvlr",
          "gilded": 0,
          "secure_media_embed": {},
          "clicked": false,
          "report_reasons": null,
          "author": "$AUTHOR",
          "media": null,
          "name": "$REFERENCE",
          "score": 3,
          "approved_by": null,
          "over_18": false,
          "removal_reason": null,
          "hidden": false,
          "preview": {
            "images": [
              {
                "source": {
                  "url": "https://i.redditmedia.com/dGBqg3w8oRFQosKPyGhUEkKY-ETPoV6WN1uof4P1R1o.jpg?s=681b2f367bfec4c7c926f8adf6d447da",
                  "width": 1877,
                  "height": 1500
                },
                "resolutions": [
                  {
                    "url": "https://i.redditmedia.com/dGBqg3w8oRFQosKPyGhUEkKY-ETPoV6WN1uof4P1R1o.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=108&amp;s=22e0a657ec076137e6be6e27da1c3c21",
                    "width": 108,
                    "height": 86
                  },
                  {
                    "url": "https://i.redditmedia.com/dGBqg3w8oRFQosKPyGhUEkKY-ETPoV6WN1uof4P1R1o.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=216&amp;s=db16bdfbcf20b0b3d044359d48668298",
                    "width": 216,
                    "height": 172
                  },
                  {
                    "url": "https://i.redditmedia.com/dGBqg3w8oRFQosKPyGhUEkKY-ETPoV6WN1uof4P1R1o.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=320&amp;s=b543be8ce95b54622c7973e327ff392b",
                    "width": 320,
                    "height": 255
                  },
                  {
                    "url": "https://i.redditmedia.com/dGBqg3w8oRFQosKPyGhUEkKY-ETPoV6WN1uof4P1R1o.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=640&amp;s=d2e4377d780d172f0606adfed76c2f04",
                    "width": 640,
                    "height": 511
                  },
                  {
                    "url": "https://i.redditmedia.com/dGBqg3w8oRFQosKPyGhUEkKY-ETPoV6WN1uof4P1R1o.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=960&amp;s=fc84bf032f6f2f86e58fdf705755d610",
                    "width": 960,
                    "height": 767
                  },
                  {
                    "url": "https://i.redditmedia.com/dGBqg3w8oRFQosKPyGhUEkKY-ETPoV6WN1uof4P1R1o.jpg?fit=crop&amp;crop=faces%2Centropy&amp;arh=2&amp;w=1080&amp;s=e6af2907b0ca084987592f307bf9d381",
                    "width": 1080,
                    "height": 863
                  }
                ],
                "variants": {},
                "id": "hQ_QkpilMBrZNb5UXVWX70P0XDrv5fWy3oBESEYjzuY"
              }
            ]
          },
          "thumbnail": "$THUMBNAIL",
          "subreddit_id": "t5_2qlqh",
          "edited": false,
          "link_flair_css_class": "samsung",
          "author_flair_css_class": null,
          "downs": $DOWNS,
          "mod_reports": [],
          "archived": false,
          "media_embed": {},
          "post_hint": "link",
          "is_self": false,
          "hide_score": true,
          "spoiler": false,
          "permalink": "$LINK",
          "locked": false,
          "stickied": false,
          "created": 1485637324,
          "url": "https://twitter.com/VenyaGeskin1/status/825329388573048833",
          "author_flair_text": null,
          "quarantine": false,
          "title": "$TITLE",
          "created_utc": $CREATED,
          "link_flair_text": "Samsung",
          "distinguished": null,
          "num_comments": $COMMENTS,
          "visited": false,
          "num_reports": null,
          "ups": $UPS
        }
        """

        fun makeModel(): Post {
            val model = Post()
            model.title = TITLE
            model.author = AUTHOR
            model.thumbnail = THUMBNAIL
            model.link = LINK
            model.created = CREATED
            model.comments = COMMENTS
            model.downs = DOWNS
            model.ups = UPS
            model.reference = REFERENCE
            return model
        }

    }

    object postDetailData {

        val JSON = """
        {
          "modhash": "",
          "children": [${postDetailDataChildren.JSON}],
          "after": null,
          "before": null
        }
        """

        fun makeModel(): PostDetailData {
            val model = PostDetailData()
            model.children = listOf(postDetailDataChildren.makeModel())
            return model
        }

    }

    object postDetailDataChildren {

        val KIND = "t1"
        val JSON = """
        {
          "kind": "$KIND",
          "data": ${comment.JSON}
        }
        """

        fun makeModel(): PostDetailDataChildren {
            val model = PostDetailDataChildren()
            model.kind = KIND
            model.comment = comment.makeModel()
            return model
        }

    }

    object postDetailDataReply {

        val JSON = """
        {
          "kind": "t1",
          "data": ${postDetailData.JSON}
        }
        """

        fun makeModel(): PostDetailDataReply {
            val model = PostDetailDataReply()
            model.data = postDetailData.makeModel()
            return model
        }

    }

    object postDetailResponse {

        val JSON = """
        {
          "kind": "Listing",
          "data": ${postDetailData.JSON}
        }
        """
        val SERVICE_JSON = """
        [
          {
            "kind": "Listing",
            "data": {
              "modhash": "",
              "children": [
                {
                  "kind": "t3",
                  "data": {
                    "contest_mode": false,
                    "banned_by": null,
                    "media_embed": {},
                    "subreddit": "Android",
                    "selftext_html": null,
                    "selftext": "",
                    "likes": null,
                    "suggested_sort": null,
                    "user_reports": [],
                    "secure_media": null,
                    "saved": false,
                    "id": "5s1dkg",
                    "gilded": 0,
                    "secure_media_embed": {},
                    "clicked": false,
                    "report_reasons": null,
                    "author": "shivamchatak",
                    "media": null,
                    "score": 26,
                    "approved_by": null,
                    "over_18": false,
                    "domain": "forums.crackberry.com",
                    "hidden": false,
                    "num_comments": 3,
                    "thumbnail": "default",
                    "subreddit_id": "t5_2qlqh",
                    "edited": false,
                    "link_flair_css_class": null,
                    "author_flair_css_class": null,
                    "downs": 0,
                    "archived": false,
                    "removal_reason": null,
                    "stickied": false,
                    "is_self": false,
                    "hide_score": false,
                    "spoiler": false,
                    "permalink": "/r/Android/comments/5s1dkg/blackberry_priv_starts_getting_february_security/",
                    "locked": false,
                    "name": "t3_5s1dkg",
                    "created": 1486253188,
                    "url": "http://forums.crackberry.com/blackberry-priv-f440/february-5th-2017-security-patch-available-1098992/",
                    "author_flair_text": null,
                    "quarantine": false,
                    "title": "BlackBerry Priv starts getting February security update (February 5, 2017)",
                    "created_utc": 1486224388,
                    "link_flair_text": null,
                    "ups": 26,
                    "upvote_ratio": 0.85,
                    "mod_reports": [],
                    "visited": false,
                    "num_reports": null,
                    "distinguished": null
                  }
                }
              ],
              "after": null,
              "before": null
            }
          },
          $JSON
          ]
        """

        fun makeModel(): PostDetailResponse {
            val model = PostDetailResponse()
            model.data = postDetailData.makeModel()
            return model
        }

    }

    object postResponse {

        val JSON = """
        {
            "kind": "Listing",
            "data": ${data.JSON}
        }
        """

        fun makeModel(): PostResponse {
            val model = PostResponse()
            model.data = data.makeModel()
            return model
        }

    }

}
