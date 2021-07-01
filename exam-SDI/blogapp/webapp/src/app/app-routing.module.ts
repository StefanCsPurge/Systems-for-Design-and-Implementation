import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserhelloComponent} from "./userhello/userhello.component";
import {UserAndFollowersComponent} from "./userhello/user-and-followers/user-and-followers.component";
import {UserAndFollowersPostsComponent} from "./userhello/user-and-followers-posts/user-and-followers-posts.component";


const routes: Routes = [
  // { path: '', redirectTo: '/home', pathMatch: 'full' },
  //{path: 'movieapp/movies', component: MoviesComponent},
  {path: 'user-hello/:id', component: UserhelloComponent},
  {path: 'user-followers/:id', component: UserAndFollowersComponent},
  {path: 'user-posts-followers/:id', component: UserAndFollowersPostsComponent},

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
