import {Follower} from "./follower.model";
import {Post} from "./post.model";

export class UserFollowersPosts{
  name: string;
  followers: Follower[];
  posts: Post[];
}
