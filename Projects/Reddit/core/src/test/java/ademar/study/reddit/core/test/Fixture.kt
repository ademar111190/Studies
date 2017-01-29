package ademar.study.reddit.core.test

import ademar.study.reddit.core.model.Error
import ademar.study.reddit.core.model.HelloWorld
import ademar.study.reddit.core.model.Post
import ademar.study.reddit.core.model.internal.Child
import ademar.study.reddit.core.model.internal.Data
import ademar.study.reddit.core.model.internal.PostResponse
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

    object helloWorld {

        val MESSAGE = "Hello World!"
        val JSON = """
        {
            "message": "$MESSAGE"
        }
        """

        fun makeModel(): HelloWorld {
            val model = HelloWorld()
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
          "permalink": "/r/Android/comments/5qpvlr/galaxy_s8_press_render_leak/",
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
            model.created = CREATED
            model.comments = COMMENTS
            model.downs = DOWNS
            model.ups = UPS
            model.reference = REFERENCE
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
