package br.com.tmsfasdom.security;

import java.util.Hashtable;

import javax.naming.AuthenticationException;
import javax.naming.CommunicationException;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import javax.naming.ldap.InitialLdapContext;
import javax.naming.ldap.LdapContext;

import br.com.tmsfasdom.model.RetornoAD;



public class AuxAD {

	public static final String ERROCONEXAO = "Erro na comunicação com o AD.";
	public static final String SUCESSO = "Sucesso";
	public static final String ERROAUTENTICACAO = "Erro na autenticação, usuario ou senha invalidos.";

	public RetornoAD validaUsuarioAD(String ipDominio, String usuario, String senha, String dominio) {
		LdapContext ctx = null;
		try {
			Hashtable<String, String> env = new Hashtable<String, String>();
			env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
			env.put(Context.SECURITY_AUTHENTICATION, "Simple");
			String user = usuario + "@" + dominio;
			env.put(Context.SECURITY_PRINCIPAL, user);
			env.put(Context.SECURITY_CREDENTIALS, senha);
			String url = "ldap://" + ipDominio + ":389";
			env.put(Context.PROVIDER_URL, url);
			ctx = new InitialLdapContext(env, null);
			System.out.println("Connection Successful.");
			String[] strDominioPartida = dominio.split("\\.");
			String ldapSearchBase = "";
			for (int i = 0; i < strDominioPartida.length; i++) {
				if (i == strDominioPartida.length - 1) {
					ldapSearchBase = ldapSearchBase + "dc=" + strDominioPartida[i];
				} else {
					ldapSearchBase = ldapSearchBase + "dc=" + strDominioPartida[i] + ",";

				}
			}
			RetornoAD dadosRetorno;
			dadosRetorno = montaRetorno(buscaDadosUsuario(ctx, ldapSearchBase, usuario));
			ctx.close();
			ctx = null;
			return dadosRetorno;
		} catch (AuthenticationException e) {
			System.out.println(ERROAUTENTICACAO);
			RetornoAD dadosRetorno = new RetornoAD(null, null, null, null, ERROAUTENTICACAO);
			return dadosRetorno;
		} catch (CommunicationException e) {
			System.out.println(ERROCONEXAO);
			RetornoAD dadosRetorno = new RetornoAD(null, null, null, null, ERROCONEXAO);
			return dadosRetorno;
		} catch (Exception e) {
			RetornoAD dadosRetorno = new RetornoAD(null, null, null, null, e.getMessage());
			return dadosRetorno;
		} finally {
			if (ctx != null) {
				ctx = null;
			}
		}
	}

	private RetornoAD montaRetorno(SearchResult resultado) {
		RetornoAD retorno = new RetornoAD();
		if (resultado.getAttributes().get("givenname") != null)
			retorno.setPrimeiroNome(resultado.getAttributes().get("givenname").toString().split(":")[1].trim());
		if (resultado.getAttributes().get("sn")!= null)
			retorno.setUltimoNome(resultado.getAttributes().get("sn").toString().split(":")[1].trim());
		if (resultado.getAttributes().get("samaccountname") != null)
			retorno.setLogin(resultado.getAttributes().get("samaccountname").toString().split(":")[1].trim());
		if (resultado.getAttributes().get("mail") != null)
			retorno.setEmail(resultado.getAttributes().get("mail").toString().split(":")[1].trim());
		retorno.setMensagem(SUCESSO);
		return retorno;
	}

	private SearchResult buscaDadosUsuario(DirContext ctx, String ldapSearchBase, String accountName)
			throws NamingException {

		String searchFilter = "(&(objectClass=user)(sAMAccountName=" + accountName + "))";

		SearchControls searchControls = new SearchControls();
		searchControls.setSearchScope(SearchControls.SUBTREE_SCOPE);

		NamingEnumeration<SearchResult> results = ctx.search(ldapSearchBase, searchFilter, searchControls);

		SearchResult searchResult = null;
		if (results.hasMoreElements()) {
			searchResult = (SearchResult) results.nextElement();

			// make sure there is not another item available, there should be
			// only 1 match
			if (results.hasMoreElements()) {
				System.err.println("Mais de um usuario encontrado para a o login: " + accountName);
				return null;
			}
		}
		return searchResult;
	}

}