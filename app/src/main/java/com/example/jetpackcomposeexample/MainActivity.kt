package com.example.jetpackcomposeexample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.*
import androidx.ui.foundation.Clickable
import androidx.ui.foundation.HorizontalScroller
import androidx.ui.foundation.Image
import androidx.ui.foundation.VerticalScroller
import androidx.ui.foundation.shape.corner.RoundedCornerShape
import androidx.ui.graphics.ScaleFit
import androidx.ui.graphics.imageFromResource
import androidx.ui.layout.*
import androidx.ui.material.Divider
import androidx.ui.material.MaterialTheme
import androidx.ui.text.font.FontWeight
import androidx.ui.tooling.preview.Preview
import androidx.ui.unit.Dp
import androidx.ui.unit.dp

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MyApp {
                MainScreen(
                    stories = stories,
                    feeds = feeds
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    stories: List<Story>,
    feeds: List<Feed>
) {
    VerticalScroller {
        Column {
            StoriesView(stories)
            Divider(color = primaryDark)
            FeedContent(feeds)
        }
    }
}

@Composable
fun StoriesView(stories: List<Story>) {
    HorizontalScroller {
        Row(modifier = LayoutPadding(8.dp)) {
            stories.forEach { story ->
                Column(
                    modifier = LayoutPadding(
                        start = 12.dp,
                        end = 12.dp
                    )
                ) {
                    Container(width = 50.dp, height = 50.dp) {
                        RoundedImage(imageResId = story.image)
                    }

                    Text(
                        modifier = LayoutGravity.Center,
                        text = story.username,
                        style = MaterialTheme.typography().caption
                    )
                }
            }
        }
    }
}

@Composable
fun FeedContent(feeds: List<Feed>) {
    Column(modifier = LayoutWidth.Fill) {
        feeds.forEach { feed ->
            FeedHeader(feed)
            FeedMediaActions(feed)
        }
    }
}

@Composable
fun FeedHeader(feed: Feed) {
    Row(modifier = LayoutPadding(16.dp)) {
        Row(modifier = LayoutWeight(1f)) {
            Container(width = 30.dp, height = 30.dp) {
                RoundedImage(imageResId = feed.userImage)
            }
            Text(
                text = feed.username,
                modifier = LayoutPadding(start = 8.dp) + LayoutGravity.Center
            )
        }

        IconContainer(iconResId = R.drawable.ic_options)
    }
}

@Composable
fun FeedMediaActions(feed: Feed) {
    Column {
        Container(modifier = LayoutHeight.Min(180.dp) + LayoutWidth.Fill) {
            Image(
                scaleFit = ScaleFit.FillHeight,
                image = imageFromResource(
                    ContextAmbient.current.resources,
                    feed.feedImage
                )
            )
        }

        Row(modifier = LayoutPadding(16.dp)) {
            Row(modifier = LayoutWeight(1f)) {
                Clickable(onClick = {
                    feed.like.isLiked = !feed.like.isLiked

                    if (feed.like.isLiked) {
                        feed.like.qtd++
                        feed.like.iconState = R.drawable.ic_heart_filled
                    } else {
                        feed.like.qtd--
                        feed.like.iconState = R.drawable.ic_heart
                    }
                }) {
                    IconContainer(iconResId = feed.like.iconState)
                }
                IconContainer(
                    modifier = LayoutPadding(start = 8.dp),
                    iconResId = R.drawable.ic_comment
                )
                IconContainer(
                    modifier = LayoutPadding(start = 8.dp),
                    iconResId = R.drawable.ic_share
                )
            }

            IconContainer(iconResId = R.drawable.ic_save_post)
        }

        Text(
            text = "${feed.like.qtd} curtidas",
            modifier = LayoutPadding(start = 16.dp),
            style = MaterialTheme.typography().subtitle2.copy(fontWeight = FontWeight.Bold)
        )
    }
}

@Composable
fun IconContainer(modifier: Modifier = Modifier.None, iconResId: Int) {
    Container(width = 30.dp, height = 30.dp, modifier = modifier) {
        Image(
            image = imageFromResource(
                ContextAmbient.current.resources,
                iconResId
            )
        )
    }
}

@Composable
fun RoundedImage(imageResId: Int, roundedCornerShapeSize: Dp = 50.dp) {
    Image(
        modifier = drawClip(RoundedCornerShape(roundedCornerShapeSize)),
        image = imageFromResource(
            ContextAmbient.current.resources,
            imageResId
        )
    )
}

@Preview("Stores View")
@Composable
fun DefaultPreview() {
    MyApp {
        MainScreen(
            stories = stories,
            feeds = feeds
        )
    }
}