/*
package co.honobono.hncore.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import twitter4j.IDs;
import twitter4j.StatusUpdate;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

public class TwitterUtil {
	private final static String ConsumerKey = "";
	private final static String ConsumerSecret = "";
	private final static String AccessToken = "";
	private final static String AccessTokenSecret = "";
	private static TwitterFactory twitterfactory = new TwitterFactory();
	private static AccessToken accesstoken = new AccessToken(AccessToken, AccessTokenSecret);
	private static Twitter twitter = twitterfactory.getInstance();

	static {
		twitter.setOAuthConsumer(ConsumerKey, ConsumerSecret);
		twitter.setOAuthAccessToken(accesstoken);
	}

	public static void tweet(String msg) throws TwitterException {
		twitter.updateStatus(msg);
	}

	public static void tweet(String msg, File... picture) throws TwitterException {
		if (picture.length > 5) {
			throw new TwitterException("写真は5枚までです。");
		}
		long[] mediaIDs = new long[5];
		for (int i = 0; i < mediaIDs.length; i++) {
			if (picture[i] != null) {
				mediaIDs[i] = twitter.uploadMedia(picture[i]).getMediaId();
			}
		}
		StatusUpdate update = new StatusUpdate(msg);
		update.setMediaIds(mediaIDs);
		twitter.updateStatus(update);
	}

	public static void tweet(String msg, Map<String, InputStream> picture) throws TwitterException {
		if (picture.size() > 5) {
			throw new TwitterException("写真は5枚までです。");
		}
		List<Long> mediaIDs = new ArrayList<Long>();
		for (Map.Entry<String, InputStream> e : picture.entrySet()) {
			mediaIDs.add(twitter.uploadMedia(e.getKey(), e.getValue()).getMediaId());
		}
		StatusUpdate update = new StatusUpdate(msg);
		update.setMediaIds(tolong(mediaIDs));
		twitter.updateStatus(update);
	}

	public static void Followback() throws TwitterException {
		IDs frends = twitter.getFriendsIDs(-1);
		IDs follower = twitter.getFollowersIDs(-1);
		List<Long> notFollowIdList = new ArrayList<Long>();
		for (long id : follower.getIDs()) {
			if (!contains(frends, id)) {
				notFollowIdList.add(id);
			}
		}
		doFollow(notFollowIdList);
	}

	protected static void doFollow(List<Long> notFollowIdList) throws TwitterException {
		for (Long userId : notFollowIdList) {
			User user = twitter.createFriendship(userId);
			if (user == null) {
				throw new TwitterException("フォローに失敗しました。対象ID：" + userId);
			}
		}
	}

	private static boolean contains(IDs frends, long id) {

		for (long frendId : frends.getIDs()) {
			if (frendId == id) {
				return true;
			}
		}
		return false;
	}

	private static long[] tolong(List<Long> l) {
		long[] list = new long[l.size()];
		for (int i = 0; i < l.size(); i++) {
			list[i] = l.get(i);
		}
		return list;
	}

	public static void Update(StatusUpdate update) throws TwitterException {
		new Thread() {
			@Override
			public void run() {
				try {
					twitter.updateStatus(update);
				} catch (TwitterException e) { }
			}
		}.start();
	}
}

*/