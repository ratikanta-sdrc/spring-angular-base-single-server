package org.sdrc.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.sdrc.domain.RoleFeaturePermissionScheme;
import org.sdrc.domain.User;
import org.sdrc.domain.UserAreaMapping;
import org.sdrc.domain.UserRoleFeaturePermissionMapping;
import org.sdrc.repository.UserAreaMappingRepository;
import org.sdrc.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	UserAreaMappingRepository userAreaMappingRepository;
	

	/*
	 * 
	 * 
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 * 
	 * Here,we are overriding spring security loadbyUsername method. Note: We cannot apply @Transactional directly because,
	 * Spring uses JDK-Proxy by default.So AspectJ required for class Injection directly from container.
	 * 
	 * Authorities are ADDED IN format of : ROLE_NAME:Feature,Permission. So @PreAuthorize annotation will be like
	 * 
	 * @PreAuthorize("hasRole('CCI:data_entry,edit')")
	 * 
	 * Note : If you want to remove Role specifics, you can remove Role Name from GrantedAuthority
	 * 
	 * @PreAuthorize("hasRole('data_entry,edit')")
	 */

	@Override
	@Transactional
	public User loadUserByUsername(String userName) throws UsernameNotFoundException {
		
		User user = userRepository.findByUserName(userName);

		if (user == null) {
			throw new UsernameNotFoundException("Invalid username or password !");
		}
		
		

		Collection<UserAreaMapping> areaMappings = userAreaMappingRepository.findByUser(user);
		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();

		for (UserAreaMapping userAreaMapping : areaMappings) {
			List<UserRoleFeaturePermissionMapping> featureAndPermissions = userAreaMapping.getUserRoleFeaturePermissionMappings();
			for (UserRoleFeaturePermissionMapping userRoleFeaturePermissionMapping : featureAndPermissions) {
				RoleFeaturePermissionScheme roleFeaturePermissionScheme = userRoleFeaturePermissionMapping.getRoleFeaturePermissionScheme();
				
				//adding authorities using syntax : (Role:Feature,Permission)
				grantedAuthorities.add(new SimpleGrantedAuthority(
						roleFeaturePermissionScheme.getRole().getRoleName().concat(":").
						concat(roleFeaturePermissionScheme.getFeaturePermissionMapping().getFeature().getFeatureName())
						.concat(",")
						.concat
						(roleFeaturePermissionScheme.getFeaturePermissionMapping().getPermission().getPermissionName())));
			
				//adding authorities using syntax : (Feature,Permission)
				grantedAuthorities.add(new SimpleGrantedAuthority(
						roleFeaturePermissionScheme.getFeaturePermissionMapping().getFeature().getFeatureName()
						.concat(",")
						.concat
						(roleFeaturePermissionScheme.getFeaturePermissionMapping().getPermission().getPermissionName())));
			
				logger.info("\n<Granted Authorities To User> , {} \n",roleFeaturePermissionScheme.getRole().getRoleName().concat(":").
							concat(roleFeaturePermissionScheme.getFeaturePermissionMapping().getFeature().getFeatureName())
							.concat(",")
							.concat
							(roleFeaturePermissionScheme.getFeaturePermissionMapping().getPermission().getPermissionName()));
			
			user.setAuthorities(grantedAuthorities);
			}
		}
		return user;
	}

}
