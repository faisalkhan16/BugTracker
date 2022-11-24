package com.codebyte.bugservice;

import com.codebyte.bugservice.entity.Bug;
import com.codebyte.bugservice.entity.Comment;
import com.codebyte.bugservice.repository.BugRepository;
import com.codebyte.bugservice.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.time.LocalDate;

@SpringBootApplication
@EnableEurekaClient
@RequiredArgsConstructor
public class BugServiceApplication implements CommandLineRunner {

	private final BugRepository bugRepository;
	private final CommentRepository commentRepository;

	public static void main(String[] args) {
		SpringApplication.run(BugServiceApplication.class, args);
	}

	@Override
	public void run(String... args) {
		if (bugRepository.count() < 1) {
			Bug bug = new Bug();
			bug.setName("User Service Bug");
			bug.setDescription("User Service Is Not Working");
			bug.setStatus(0);
			bug.setSeverity(3);
			bug.setProductUlid("01GGWM8SNJ94HTBXSK8BFT2VA6");
			bug.setAssignByUlid("01GGWK8CADQC31446200AVN5SA");
			bug.setAssignToUlid("01GGTFP1AJA2C4P0HNM0R9YPDG");
			bug.setCreatedDate(LocalDate.now());
			bugRepository.save(bug);

			Comment comment = new Comment();
			comment.setCommentUlid("01GH22310TYH2KVSX4JJXX9MN3");
			comment.setDescription("Bug is not fixed");
			comment.setCreatedBy("01GGWK8CADQC31446200AVN5SA");
			comment.setCreatedDate(LocalDate.now());
			comment.setBugId(1l);
			commentRepository.save(comment);



			Bug bug2 = new Bug();
			bug2.setName("Product Service Bug");
			bug2.setDescription("Product Service Is Not Working");
			bug2.setStatus(1);
			bug2.setSeverity(3);
			bug2.setProductUlid("01GGWM93GG13NCD5BY4JF9159A");
			bug2.setAssignByUlid("01GGWK8CADQC31446200AVN5SA");
			bug2.setAssignToUlid("01GGTFPBDTP8R9YHP1V9HNPSN2");
			bug2.setCreatedDate(LocalDate.now());
			bugRepository.save(bug2);

			Comment comment2 = new Comment();
			comment2.setCommentUlid("01GH22524S13YQV8GCG1XVNYSP");
			comment2.setDescription("Working on bug");
			comment2.setCreatedBy("01GGWK8CADQC31446200AVN5SA");
			comment2.setCreatedDate(LocalDate.now());
			comment2.setBugId(2l);
			commentRepository.save(comment2);

			Comment comment3 = new Comment();
			comment3.setCommentUlid("01GH225V8G8JWEA4PNR4QACD4Y");
			comment3.setDescription("Bug is resolved");
			comment3.setCreatedBy("01GGWK8CADQC31446200AVN5SA");
			comment3.setCreatedDate(LocalDate.now());
			comment3.setBugId(2l);
			commentRepository.save(comment3);

		}
	}

}
